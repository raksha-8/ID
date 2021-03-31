package com.storedobject.neem.INCIDENT.logic;

import com.storedobject.core.*;
import com.storedobject.ui.DetailLinkGrid;
import com.storedobject.ui.LinkGrid;
import com.storedobject.ui.ObjectEditor;
import com.storedobject.ui.ObjectLinkField;
import com.storedobject.ui.inventory.LocateItem;
import com.storedobject.vaadin.Button;
import com.vaadin.flow.component.icon.VaadinIcon;

public class ID extends ObjectEditor<InventoryItemType> {

    public ID() {
        super(InventoryItemType.class);
    }

    @Override
    protected LinkGrid<?> createLinkFieldGrid(String fieldName, ObjectLinkField<?> field) {
        if ("Alternate reason.l".equals(fieldName)) {
            //noinspection unchecked
            return new APNGrid((ObjectLinkField<InventoryAPN>) field);
        }
        return super.createLinkFieldGrid(fieldName, field);
    }

    private static class APNGrid extends DetailLinkGrid<InventoryAPN> {

        public APNGrid(ObjectLinkField<InventoryAPN> linkField) {
            super(linkField);
        }

        @Override
        public void constructed() {

            getButtonPanel().add(new Button("View Stock", VaadinIcon.STOCK, e -> viewStock()).asSmall());

        }

        private void viewstock() {
            if (size() == 0) {
                message("No entries to select");
                return;
            }
            InventoryAPN apn = getSelected();
            if (apn == null) {
                message("Select an entry");
                return;
            }
            new LocateItem(apn.getReason()).execute();
        }
    }

    @Override
    public void validateData() throws Exception {
        throw new SOException("Changes can not be saved... ");
    }
}

