//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.12.17 at 02:51:19 PM IST 
//


package cdot.ccsp.utils;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

import cdot.onem2m.resource.xsd.AnnouncedFlexContainerResource;
import cdot.onem2m.resource.xsd.ChildResourceRef;
import cdot.onem2m.resource.xsd.Subscription;


/**
 * <p>Java class for devicePulseOximeterAnnc complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="devicePulseOximeterAnnc">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.onem2m.org/xml/protocols}announcedFlexContainerResource">
 *       &lt;sequence>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="childResource" type="{http://www.onem2m.org/xml/protocols}childResourceRef" maxOccurs="unbounded"/>
 *           &lt;choice maxOccurs="unbounded">
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}pulsemeter"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}pulsemeterAnnc"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}oximeter"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}oximeterAnnc"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}battery"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}batteryAnnc"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols}subscription"/>
 *           &lt;/choice>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "devicePulseOximeterAnnc", propOrder = {
    "childResource",
    "pulsemeterOrPulsemeterAnncOrOximeter"
})
public class DevicePulseOximeterAnnc
    extends AnnouncedFlexContainerResource
{

    protected List<ChildResourceRef> childResource;
    @XmlElements({
        @XmlElement(name = "pulsemeter", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = Pulsemeter.class),
        @XmlElement(name = "pulsemeterAnnc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = PulsemeterAnnc.class),
        @XmlElement(name = "oximeter", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = Oximeter.class),
        @XmlElement(name = "oximeterAnnc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = OximeterAnnc.class),
        @XmlElement(name = "battery", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = Battery.class),
        @XmlElement(name = "batteryAnnc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = BatteryAnnc.class),
        @XmlElement(name = "subscription", namespace = "http://www.onem2m.org/xml/protocols", type = Subscription.class)
    })
    protected List<Object> pulsemeterOrPulsemeterAnncOrOximeter;

    /**
     * Gets the value of the childResource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the childResource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChildResource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChildResourceRef }
     * 
     * 
     */
    public List<ChildResourceRef> getChildResource() {
        if (childResource == null) {
            childResource = new ArrayList<ChildResourceRef>();
        }
        return this.childResource;
    }

    /**
     * Gets the value of the pulsemeterOrPulsemeterAnncOrOximeter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pulsemeterOrPulsemeterAnncOrOximeter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPulsemeterOrPulsemeterAnncOrOximeter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Pulsemeter }
     * {@link PulsemeterAnnc }
     * {@link Oximeter }
     * {@link OximeterAnnc }
     * {@link Battery }
     * {@link BatteryAnnc }
     * {@link Subscription }
     * 
     * 
     */
    public List<Object> getPulsemeterOrPulsemeterAnncOrOximeter() {
        if (pulsemeterOrPulsemeterAnncOrOximeter == null) {
            pulsemeterOrPulsemeterAnncOrOximeter = new ArrayList<Object>();
        }
        return this.pulsemeterOrPulsemeterAnncOrOximeter;
    }

}
