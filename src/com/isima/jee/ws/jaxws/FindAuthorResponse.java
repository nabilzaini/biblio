
package com.isima.jee.ws.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "findAuthorResponse", namespace = "http://ws.jee.isima.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findAuthorResponse", namespace = "http://ws.jee.isima.com/")
public class FindAuthorResponse {

    @XmlElement(name = "return", namespace = "")
    private List<com.isima.jee.models.Author> _return;

    /**
     * 
     * @return
     *     returns List<Author>
     */
    public List<com.isima.jee.models.Author> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<com.isima.jee.models.Author> _return) {
        this._return = _return;
    }

}
