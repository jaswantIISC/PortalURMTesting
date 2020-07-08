//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.12.17 at 02:51:19 PM IST 
//


package cdot.ccsp.utils;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;

import cdot.onem2m.resource.xsd.AnnouncedFlexContainerResource;
import cdot.onem2m.resource.xsd.ChildResourceRef;
import cdot.onem2m.resource.xsd.Subscription;


/**
 * <p>Java class for deviceSmartPlugAnnc complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deviceSmartPlugAnnc">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.onem2m.org/xml/protocols}announcedFlexContainerResource">
 *       &lt;sequence>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="childResource" type="{http://www.onem2m.org/xml/protocols}childResourceRef" maxOccurs="unbounded"/>
 *           &lt;choice maxOccurs="unbounded">
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}binarySwitch"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}binarySwitchAnnc"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}faultDetection"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}faultDetectionAnnc"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}remoteControlEnable"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}remoteControlEnableAnnc"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}powerOutlet0"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}powerOutlet0Annc"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}powerOutlet1"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}powerOutlet1Annc"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}powerOutlet2"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}powerOutlet2Annc"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}powerOutlet3"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}powerOutlet3Annc"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}powerOutlet4"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}powerOutlet4Annc"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}powerOutlet5"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}powerOutlet5Annc"/>
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
@XmlType(name = "deviceSmartPlugAnnc", propOrder = {
    "childResource",
    "binarySwitchOrBinarySwitchAnncOrFaultDetection"
})
public class DeviceSmartPlugAnnc
    extends AnnouncedFlexContainerResource
{

    protected List<ChildResourceRef> childResource;
    @XmlElementRefs({
        @XmlElementRef(name = "remoteControlEnable", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "binarySwitch", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "binarySwitchAnnc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "powerOutlet0", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "powerOutlet2Annc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "powerOutlet1", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "powerOutlet0Annc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "faultDetectionAnnc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "powerOutlet3", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "powerOutlet4", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "powerOutlet4Annc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "powerOutlet1Annc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "powerOutlet2", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "remoteControlEnableAnnc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "faultDetection", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "powerOutlet5Annc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "subscription", namespace = "http://www.onem2m.org/xml/protocols", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "powerOutlet5", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "powerOutlet3Annc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> binarySwitchOrBinarySwitchAnncOrFaultDetection;

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
     * Gets the value of the binarySwitchOrBinarySwitchAnncOrFaultDetection property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the binarySwitchOrBinarySwitchAnncOrFaultDetection property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBinarySwitchOrBinarySwitchAnncOrFaultDetection().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link RemoteControlEnable }{@code >}
     * {@link JAXBElement }{@code <}{@link BinarySwitch }{@code >}
     * {@link JAXBElement }{@code <}{@link BinarySwitchAnnc }{@code >}
     * {@link JAXBElement }{@code <}{@link SubDevicePowerOutlet }{@code >}
     * {@link JAXBElement }{@code <}{@link SubDevicePowerOutletAnnc }{@code >}
     * {@link JAXBElement }{@code <}{@link SubDevicePowerOutlet }{@code >}
     * {@link JAXBElement }{@code <}{@link SubDevicePowerOutletAnnc }{@code >}
     * {@link JAXBElement }{@code <}{@link FaultDetectionAnnc }{@code >}
     * {@link JAXBElement }{@code <}{@link SubDevicePowerOutlet }{@code >}
     * {@link JAXBElement }{@code <}{@link SubDevicePowerOutlet }{@code >}
     * {@link JAXBElement }{@code <}{@link SubDevicePowerOutletAnnc }{@code >}
     * {@link JAXBElement }{@code <}{@link SubDevicePowerOutletAnnc }{@code >}
     * {@link JAXBElement }{@code <}{@link SubDevicePowerOutlet }{@code >}
     * {@link JAXBElement }{@code <}{@link RemoteControlEnableAnnc }{@code >}
     * {@link JAXBElement }{@code <}{@link FaultDetection }{@code >}
     * {@link JAXBElement }{@code <}{@link SubDevicePowerOutletAnnc }{@code >}
     * {@link JAXBElement }{@code <}{@link Subscription }{@code >}
     * {@link JAXBElement }{@code <}{@link SubDevicePowerOutlet }{@code >}
     * {@link JAXBElement }{@code <}{@link SubDevicePowerOutletAnnc }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getBinarySwitchOrBinarySwitchAnncOrFaultDetection() {
        if (binarySwitchOrBinarySwitchAnncOrFaultDetection == null) {
            binarySwitchOrBinarySwitchAnncOrFaultDetection = new ArrayList<JAXBElement<?>>();
        }
        return this.binarySwitchOrBinarySwitchAnncOrFaultDetection;
    }

}
