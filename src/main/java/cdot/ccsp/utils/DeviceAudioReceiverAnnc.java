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
 * <p>Java class for deviceAudioReceiverAnnc complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="deviceAudioReceiverAnnc">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.onem2m.org/xml/protocols}announcedFlexContainerResource">
 *       &lt;sequence>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="childResource" type="{http://www.onem2m.org/xml/protocols}childResourceRef" maxOccurs="unbounded"/>
 *           &lt;choice maxOccurs="unbounded">
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}binarySwitch"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}binarySwitchAnnc"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}audioVolume"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}audioVolumeAnnc"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}mediaInput"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}mediaInputAnnc"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}mediaOutput"/>
 *             &lt;element ref="{http://www.onem2m.org/xml/protocols/homedomain}mediaOutputAnnc"/>
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
@XmlType(name = "deviceAudioReceiverAnnc", propOrder = {
    "childResource",
    "binarySwitchOrBinarySwitchAnncOrAudioVolume"
})
public class DeviceAudioReceiverAnnc
    extends AnnouncedFlexContainerResource
{

    protected List<ChildResourceRef> childResource;
    @XmlElementRefs({
        @XmlElementRef(name = "binarySwitch", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "binarySwitchAnnc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "mediaInputAnnc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "audioVolumeAnnc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "subscription", namespace = "http://www.onem2m.org/xml/protocols", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "audioVolume", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "mediaInput", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "mediaOutputAnnc", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "mediaOutput", namespace = "http://www.onem2m.org/xml/protocols/homedomain", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> binarySwitchOrBinarySwitchAnncOrAudioVolume;

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
     * Gets the value of the binarySwitchOrBinarySwitchAnncOrAudioVolume property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the binarySwitchOrBinarySwitchAnncOrAudioVolume property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBinarySwitchOrBinarySwitchAnncOrAudioVolume().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link BinarySwitch }{@code >}
     * {@link JAXBElement }{@code <}{@link BinarySwitchAnnc }{@code >}
     * {@link JAXBElement }{@code <}{@link MediaSelectAnnc }{@code >}
     * {@link JAXBElement }{@code <}{@link AudioVolumeAnnc }{@code >}
     * {@link JAXBElement }{@code <}{@link Subscription }{@code >}
     * {@link JAXBElement }{@code <}{@link AudioVolume }{@code >}
     * {@link JAXBElement }{@code <}{@link MediaSelect }{@code >}
     * {@link JAXBElement }{@code <}{@link MediaSelectAnnc }{@code >}
     * {@link JAXBElement }{@code <}{@link MediaSelect }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getBinarySwitchOrBinarySwitchAnncOrAudioVolume() {
        if (binarySwitchOrBinarySwitchAnncOrAudioVolume == null) {
            binarySwitchOrBinarySwitchAnncOrAudioVolume = new ArrayList<JAXBElement<?>>();
        }
        return this.binarySwitchOrBinarySwitchAnncOrAudioVolume;
    }

}
