package interfaces;

import java.util.ArrayList;

import model.Order;

public interface Observer
{
  public void update(ArrayList<Order> acitveorders,ArrayList<Order> deliveredorders );
}