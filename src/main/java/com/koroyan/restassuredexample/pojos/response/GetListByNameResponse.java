package com.koroyan.restassuredexample.pojos.response;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "GetListByNameResponse")
@XmlType(namespace = "http://tempuri.org")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GetListByNameResponse {
    @XmlElement(name = "GetListByNameResult")
    private GetListByNameResult getListByNameResult;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
