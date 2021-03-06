package net.relatedwork.client.content;

import com.google.gwt.dom.client.HeadingElement;
import com.google.gwt.dom.client.ParagraphElement;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import net.relatedwork.client.MainPresenter;
import net.relatedwork.client.place.NameTokens;
import net.relatedwork.client.tools.ListPresenter;
import net.relatedwork.client.tools.events.LoadingOverlayEvent;
import net.relatedwork.client.tools.session.SessionInformationManager;
import net.relatedwork.shared.dto.Author;
import net.relatedwork.shared.dto.DisplayPaper;
import net.relatedwork.shared.dto.DisplayPaperResult;
import net.relatedwork.shared.dto.Paper;

public class PaperPresenter extends
		Presenter<PaperPresenter.MyView, PaperPresenter.MyProxy> {

	@ContentSlot public static final Type<RevealContentHandler<?>> TYPE_Citations = new Type<RevealContentHandler<?>>();
	@ContentSlot public static final Type<RevealContentHandler<?>> TYPE_CitedBy = new Type<RevealContentHandler<?>>();
	
	public interface MyView extends View {
		public HeadingElement getRwTitle();
		public void setRwTitle(HeadingElement rwTitle);
				
		public ParagraphElement getRwAbstract();
		public void setRwAbstract(ParagraphElement rwAbstract);
		
		public HTMLPanel getRwAuthorPanel();
		public void setRwAuthorPanel(HTMLPanel rwAuthorPanel);
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.paper)
	public interface MyProxy extends ProxyPlace<PaperPresenter> {
	}


	@Inject
	public PaperPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPresenter.TYPE_SetMainContent, this);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Inject DispatchAsync dispatcher;
	
	@Inject ListPresenter<Paper> ciationsListPresenter;
	@Inject ListPresenter<Paper> citedByListPresenter;
    @Inject SessionInformationManager sessionInformationManager;


    @Override
	protected void onReveal() {
		super.onReveal();
		
		setInSlot(TYPE_Citations, ciationsListPresenter);
		setInSlot(TYPE_CitedBy, citedByListPresenter);
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);

		// Get paper_id form query string
		String paper_id = request.getParameter("q", "None");
		
		// Log paper visit
		sessionInformationManager.get().logPaper(paper_id);

		// show Loading Overlay
		getEventBus().fireEvent(new LoadingOverlayEvent(true));
		
		// Get paper data from server	
		dispatcher.execute(new DisplayPaper(paper_id), new AsyncCallback<DisplayPaperResult>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				getView().getRwTitle().setInnerHTML("Failed Request");

				// hide loading overlay
				getEventBus().fireEvent(new LoadingOverlayEvent(false));

			}

			@Override
			public void onSuccess(DisplayPaperResult result) {
				getView().getRwTitle().setInnerHTML(result.getTitle());
				getView().getRwAbstract().setInnerHTML(result.getAbstract());

				// Set authors
				getView().getRwAuthorPanel().clear();
				for (Author author: result.getAuthorList()){
					getView().getRwAuthorPanel().add(author.getAuthorLink());
				}

				// Set paper list
				ciationsListPresenter.setTitle("References");
				ciationsListPresenter.setList(result.getCitesPapers(1000),5);

				citedByListPresenter.setTitle("Cited by");
				citedByListPresenter.setList(result.getCitedByPapers(1000),5);

				// hide loading overlay
				getEventBus().fireEvent(new LoadingOverlayEvent(false));
				
			}
		});
		
	}

}
