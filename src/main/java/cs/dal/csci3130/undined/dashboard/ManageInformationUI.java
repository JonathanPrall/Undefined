package cs.dal.csci3130.undined.dashboard;

import java.util.*;

import javax.servlet.annotation.WebServlet;
import javax.swing.*;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.v7.data.fieldgroup.BeanFieldGroup;
import com.vaadin.v7.data.fieldgroup.FieldGroup;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.ui.Grid;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.FormLayout;

import cs.dal.csci3130.undined.newbackend.Restaurant;

import java.awt.*;
import com.vaadin.ui.FormLayout;
import com.vaadin.v7.ui.DateField;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.v7.data.fieldgroup.BeanFieldGroup;

@Title("Manage Information Page")
@Theme("valo")
@Widgetset("com.vaadin.v7.Vaadin7WidgetSet")

public class ManageInformationUI extends UI
{
 Button save = new Button("Save", this::save);
 Button cancel = new Button("Cancel", this::cancel);
 TextField restaurantName = new TextField("RestaurantName");
 TextField op = new TextField("OpenHour");
 TextField cl = new TextField("CloseHour");
 TextField email = new TextField("Email");
 TextField phone = new TextField("Phone");
 TextField des = new TextField("Description");
 TextField available = new TextField("# of tables are available");
 Restaurant mi;

 BeanFieldGroup<Restaurant> formFieldBindings;


 private void configureComponents() {
  save.setStyleName(ValoTheme.BUTTON_PRIMARY);
  save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
  setVisible(false);
 }
 @Override
 protected void init(VaadinRequest request) {
  configureComponents();
  buildLayout();
 }
 private void buildLayout() {
  FormLayout layout = new FormLayout();
  setSizeUndefined();
  layout.setMargin(true);

  HorizontalLayout actions = new HorizontalLayout(save, cancel);
  actions.setSpacing(true);

  layout.addComponents(actions, restaurantName, op, cl, email, phone, des, available);
 }
 public void save(Button.ClickEvent event) {
  try {
   formFieldBindings.commit();
   //getUI().service.save(mi);

   String msg = String.format("Saved '%s %s'.", mi.getRestaurantName());
   Notification.show(msg, Type.TRAY_NOTIFICATION);
   //getUI().refreshAll();
  } catch (FieldGroup.CommitException e) {
  }
 }
 public void cancel(Button.ClickEvent event) {
  Notification.show("Cancelled", Type.TRAY_NOTIFICATION);
  //getUI().select(null);
 }

 void edit(Restaurant mi) {
  this.mi = mi;
  if (mi != null) {
   formFieldBindings = BeanFieldGroup.bindFieldsBuffered(mi,
     this);
   restaurantName.focus();
  }
  //setVisible(mi != null);
 }

 @WebServlet(urlPatterns = "/Manage/*", name = "ManagerServlet", asyncSupported = true)
 @VaadinServletConfiguration(ui = ManageInformationUI.class, productionMode = false)
 public static class MyUIServlet extends VaadinServlet {
 }
}

