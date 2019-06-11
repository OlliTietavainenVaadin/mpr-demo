package org.vaadin.mprdemo;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.Version;
import com.vaadin.mpr.core.HasLegacyComponents;
import com.vaadin.mpr.core.MprTheme;
import com.vaadin.ui.TabSheet;

@Route("")
@MprTheme("mytheme")
public class MyUI extends FlexLayout implements HasLegacyComponents {

    public MyUI () {
    	setSizeFull();
    	add(new Span("This is a Vaadin 14 beta 2 (Flow " + Version.getFullVersion() + ") app with mpr-v7 and Spreadsheet"));
        final TabSheet layout = new TabSheet();
        layout.addTab(new SpreadsheetView(),"Spreadsheet");
        
        add(layout);
    }

}
