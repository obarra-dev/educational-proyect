package com.obarra.jpa.model.entity;

import javax.persistence.*;

@Entity(name = "AuthorNativeEntity")
@SqlResultSetMapping(
        name="AuthorNativeEntityMapping",
        classes=@ConstructorResult(
                targetClass=AuthorSimplified.class,
                columns={@ColumnResult(name="name"),
                        @ColumnResult(name="surname")}))
@NamedNativeQuery(
        name="AuthorNativeEntity.executeQuery",
        query="select  first_name as name, last_name as surname from AUTHOR "
                ,
        resultSetMapping="AuthorNativeEntityMapping")
public class AuthorNativeEntity {

    @Id
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
