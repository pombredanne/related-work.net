package net.relatedwork.gwtp.client;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class MainView extends ViewImpl implements MainPresenter.MyView {

	private final Widget widget;

	@UiField HTMLPanel rwHeader;
	@UiField HTMLPanel rwContent;
	@UiField HTMLPanel rwSidebar;
	@UiField HTMLPanel rwFooter;
	
	public HTMLPanel getRwHeader() {
		return rwHeader;
	}

	public void setRwHeader(HTMLPanel rwHeader) {
		this.rwHeader = rwHeader;
	}

	public HTMLPanel getRwContent() {
		return rwContent;
	}

	public void setRwContent(HTMLPanel rwContent) {
		this.rwContent = rwContent;
	}

	public HTMLPanel getRwSidebar() {
		return rwSidebar;
	}

	public void setRwSidebar(HTMLPanel rwSidebar) {
		this.rwSidebar = rwSidebar;
	}

	public HTMLPanel getRwFooter() {
		return rwFooter;
	}

	public void setRwFooter(HTMLPanel rwFooter) {
		this.rwFooter = rwFooter;
	}

	public interface Binder extends UiBinder<Widget, MainView> {
	}

	@Inject
	public MainView(final Binder binder) {
		widget = binder.createAndBindUi(this);
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	@Override
	public void setInSlot(Object slot, Widget content) {
		if (slot == MainPresenter.TYPE_SetMainContent) {
			setMainContent(content);
		} else {
			super.setInSlot(slot, content);
		}
	}

	private void setMainContent(Widget content) {
		rwContent.clear();
	    
	    if (content != null) {
	      rwContent.add(content);
	    }
	}

}