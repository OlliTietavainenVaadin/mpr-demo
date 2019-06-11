package org.vaadin.mprdemo;

import com.vaadin.addon.spreadsheet.Spreadsheet;
import com.vaadin.addon.spreadsheet.Spreadsheet.CellValueChangeEvent;
import com.vaadin.addon.spreadsheet.Spreadsheet.CellValueChangeListener;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellReference;

import java.util.Set;

public class SpreadsheetView extends VerticalLayout {

    private Spreadsheet spreadsheet;

    public SpreadsheetView() {
        setSizeFull();
        setSpacing(false);
        setMargin(false);

        initSpreadsheet();
        addComponents(spreadsheet);
        setExpandRatio(spreadsheet, 1.0f);
    }

    private void initSpreadsheet() {
        spreadsheet = new Spreadsheet();
        spreadsheet.addCellValueChangeListener(new CellValueChangeListener() {
            @Override
            public void onCellValueChange(CellValueChangeEvent event) {
                Set<CellReference> changedCells = event.getChangedCells();
                if (changedCells.size() == 0) {
                    Notification.show("Cell value changed event fired, but no changed cells" );
                } else {
                    String changed = "";
                    CellReference next = changedCells.iterator().next();
                    changed += next.formatAsString() + "->" + spreadsheet.getCell(next.getRow(), next.getCol());
                    Notification.show(changed);


                }

            }
        });
        CellStyle backgroundColorStyle = spreadsheet.getWorkbook()
                .createCellStyle();
        backgroundColorStyle.setFillBackgroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
        Cell cell = spreadsheet.createCell(0, 0,
                "This is a spreadsheet");
        cell.setCellStyle(backgroundColorStyle);

        for (int i = 1; i <= 3; i++) {
            cell = spreadsheet.createCell(0, i, "");
            cell.setCellStyle(backgroundColorStyle);
        }

        spreadsheet.createCell(1, 0, "");
        spreadsheet.createCell(2, 0, "Category");
        spreadsheet.createCell(2, 1, "Amount");
        spreadsheet.createCell(3, 0, "Brand 1");
        spreadsheet.createCell(3, 1, 90d);
        spreadsheet.createCell(4, 0, "Brand 2");
        spreadsheet.createCell(4, 1, 7d);
        spreadsheet.createCell(5, 0, "Brand 3");
        spreadsheet.createCell(5, 1, 3d);
        spreadsheet.setColumnWidth(0, 130);

        spreadsheet.refreshCells(cell);
    }


}
