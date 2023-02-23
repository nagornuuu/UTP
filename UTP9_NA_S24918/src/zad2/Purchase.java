/**
 *
 *  @author Nahornyi Andrii S24918
 *
 */

package zad2;

import java.beans.*;
import java.io.Serializable;

public class Purchase implements Serializable {
    private final String prod;
    private String data;
    private double price;
    private final VetoableChangeSupport VCS = new VetoableChangeSupport(this);
    private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);

    public Purchase(String product, String data, double price) {
        this.prod = product;
        this.data = data;
        this.price = price;
    }

    public void setData(String data) {
        PCS.firePropertyChange("data", this.data, this.data = data);
    }


    public void setPrice(double price) throws PropertyVetoException {
        VCS.fireVetoableChange("price", this.price, price);
        PCS.firePropertyChange("price", this.price, this.price = price);
    }

    public void addVetoableChangeListener(VetoableChangeListener VCL) {
        VCS.addVetoableChangeListener(VCL);
    } // addVetoableChangeListener

    public void addPropertyChangeListener(PropertyChangeListener PCL) {
        PCS.addPropertyChangeListener(PCL);
    } // addPropertyChangeListener

    @Override
    public String toString() {
        return "Purchase [prod=" + prod + ", data=" + data + ", price=" + price + "]";
    }

    // inner class NewVetoableChangeListener
    public static class NewVetoableChangeListener implements VetoableChangeListener {
        @Override
        public void vetoableChange(PropertyChangeEvent PCE) throws PropertyVetoException {
            if (PCE.getPropertyName() == ("price") && (Double) PCE.getNewValue() < 1000.00) { // if price < 1000.00 throw exception
                throw new PropertyVetoException("Price change to: " + PCE.getNewValue() + " not allowed", PCE);
            }
        }
    }
    // inner class NewPropertyChangeListener
    public static class NewPropertyChangeListener implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent PCE) { // print info about changes
            System.out.println("Change value of: " + PCE.getPropertyName() + " from: " + PCE.getOldValue() + " to: " + PCE.getNewValue());
        }
    }
}