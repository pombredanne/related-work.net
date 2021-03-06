package net.relatedwork.client.gin;


import net.relatedwork.client.MainPresenter;
import net.relatedwork.client.MainView;
import net.relatedwork.client.content.AuthorPresenter;
import net.relatedwork.client.content.AuthorView;
import net.relatedwork.client.content.HomePresenter;
import net.relatedwork.client.content.HomeView;
import net.relatedwork.client.content.SearchResultPagePresenter;
import net.relatedwork.client.content.SearchResultPageView;
import net.relatedwork.client.discussion.CommentBoxPresenter;
import net.relatedwork.client.discussion.CommentBoxView;
import net.relatedwork.client.discussion.CommentsPanelPresenter;
import net.relatedwork.client.discussion.CommentsPanelView;
import net.relatedwork.client.layout.BreadcrumbsPresenter;
import net.relatedwork.client.layout.BreadcrumbsView;
import net.relatedwork.client.layout.FooterPresenter;
import net.relatedwork.client.layout.FooterView;
import net.relatedwork.client.layout.HeaderPresenter;
import net.relatedwork.client.layout.HeaderView;
import net.relatedwork.client.place.ClientPlaceManager;
import net.relatedwork.client.place.DefaultPlace;
import net.relatedwork.client.place.NameTokens;
import net.relatedwork.client.staticpresenter.AboutPresenter;
import net.relatedwork.client.staticpresenter.AboutView;
import net.relatedwork.client.staticpresenter.DataPresenter;
import net.relatedwork.client.staticpresenter.DataView;
import net.relatedwork.client.staticpresenter.ImprintPresenter;
import net.relatedwork.client.staticpresenter.ImprintView;
import net.relatedwork.client.staticpresenter.LicensePresenter;
import net.relatedwork.client.staticpresenter.LicenseView;
import net.relatedwork.client.staticpresenter.NotYetImplementedPresenter;
import net.relatedwork.client.staticpresenter.NotYetImplementedView;
import net.relatedwork.client.tools.ListEntryPresenter;
import net.relatedwork.client.tools.ListEntryView;
import net.relatedwork.client.tools.ListPresenter;
import net.relatedwork.client.tools.ListView;
import net.relatedwork.client.tools.SearchPresenter;
import net.relatedwork.client.tools.SearchView;
import net.relatedwork.client.tools.login.LoginControlsPresenter;
import net.relatedwork.client.tools.login.LoginControlsView;
import net.relatedwork.client.tools.login.LoginPopupPresenter;
import net.relatedwork.client.tools.login.LoginPopupView;
import net.relatedwork.client.tools.login.NewUserPresenter;
import net.relatedwork.client.tools.login.NewUserView;
import net.relatedwork.client.content.PaperPresenter;
import net.relatedwork.client.content.PaperView;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import net.relatedwork.client.staticpresenter.UserVerifyPresenter;
import net.relatedwork.client.staticpresenter.UserVerifyView;
import net.relatedwork.client.sidebar.SidebarPresenter;
import net.relatedwork.client.sidebar.SidebarView;
import net.relatedwork.client.sidebar.WebsiteSidebarPresenter;
import net.relatedwork.client.sidebar.WebsiteSidebarView;

public class ClientModule extends AbstractPresenterModule {

	@Override
	protected void configure() {
		install(new DefaultModule(ClientPlaceManager.class));

		bindPresenter(MainPresenter.class, MainPresenter.MyView.class,
				MainView.class, MainPresenter.MyProxy.class);

		bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.main);

		bindPresenter(FooterPresenter.class, FooterPresenter.MyView.class,
				FooterView.class, FooterPresenter.MyProxy.class);

		bindPresenter(ImprintPresenter.class, ImprintPresenter.MyView.class,
				ImprintView.class, ImprintPresenter.MyProxy.class);

		bindPresenterWidget(BreadcrumbsPresenter.class,
				BreadcrumbsPresenter.MyView.class, BreadcrumbsView.class);

		bindPresenterWidget(HomePresenter.class, HomePresenter.MyView.class,
				HomeView.class);

        bindPresenter(CommentsPanelPresenter.class, CommentsPanelPresenter.MyView.class,
                CommentsPanelView.class, CommentsPanelPresenter.MyProxy.class);

        bindPresenterWidget(CommentBoxPresenter.class,
                CommentBoxPresenter.MyView.class, CommentBoxView.class);

        bindPresenter(AboutPresenter.class, AboutPresenter.MyView.class,
				AboutView.class, AboutPresenter.MyProxy.class);

		bindPresenterWidget(NotYetImplementedPresenter.class,
				NotYetImplementedPresenter.MyView.class,
				NotYetImplementedView.class);

		bindPresenter(DataPresenter.class, DataPresenter.MyView.class,
				DataView.class, DataPresenter.MyProxy.class);

		bindPresenter(LicensePresenter.class, LicensePresenter.MyView.class,
				LicenseView.class, LicensePresenter.MyProxy.class);

		bindPresenter(AuthorPresenter.class, AuthorPresenter.MyView.class,
				AuthorView.class, AuthorPresenter.MyProxy.class);

		bindPresenterWidget(ListPresenter.class, ListPresenter.MyView.class,
				ListView.class);

		bindPresenterWidget(LoginPopupPresenter.class,
				LoginPopupPresenter.MyView.class, LoginPopupView.class);

		bindPresenter(HeaderPresenter.class, HeaderPresenter.MyView.class,
				HeaderView.class, HeaderPresenter.MyProxy.class);

		bindPresenter(SearchPresenter.class, SearchPresenter.MyView.class,
				SearchView.class, SearchPresenter.MyProxy.class);

		bindPresenter(SearchResultPagePresenter.class,
				SearchResultPagePresenter.MyView.class,
				SearchResultPageView.class,
				SearchResultPagePresenter.MyProxy.class);

		bindPresenter(LoginControlsPresenter.class,
				LoginControlsPresenter.MyView.class, LoginControlsView.class,
				LoginControlsPresenter.MyProxy.class);

		bindPresenterWidget(NewUserPresenter.class,
				NewUserPresenter.MyView.class, NewUserView.class);

		bindPresenter(PaperPresenter.class, PaperPresenter.MyView.class,
				PaperView.class, PaperPresenter.MyProxy.class);
		bindPresenterWidget(ListEntryPresenter.class,
				ListEntryPresenter.MyView.class, ListEntryView.class);

		bindPresenter(UserVerifyPresenter.class,
				UserVerifyPresenter.MyView.class, UserVerifyView.class,
				UserVerifyPresenter.MyProxy.class);

		bindPresenter(SidebarPresenter.class, SidebarPresenter.MyView.class,
				SidebarView.class, SidebarPresenter.MyProxy.class);

		bindPresenter(WebsiteSidebarPresenter.class,
				WebsiteSidebarPresenter.MyView.class, WebsiteSidebarView.class,
				WebsiteSidebarPresenter.MyProxy.class);

	}
}
