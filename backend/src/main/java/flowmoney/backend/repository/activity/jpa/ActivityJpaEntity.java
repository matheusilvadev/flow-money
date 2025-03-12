package flowmoney.backend.repository.activity.jpa;


import flowmoney.backend.domain.activity.Activity;
import flowmoney.backend.domain.activity.Type;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.List;

@Entity(name = "Activity")
@Table(name = "activities")
public class ActivityJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "value", nullable = false)
    private Float value;

    @Column(name = "type", nullable = false)
    private Integer type;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    //Default (No Argument) Constructor for the Java Persistence API
    public ActivityJpaEntity(){

    }

    //used internally to create instances of the entity with all attributes defined.
    private ActivityJpaEntity(final String anId,
                              final String aDescription,
                              final Instant aDate,
                              final Float aValue,
                              final Integer aType,
                              final Instant aCreatedAt,
                              final Instant anUpdatedAt){

        this.id = anId;
        this.description = aDescription;
        this.date = aDate;
        this.value = aValue;
        this.type = aType;
        this.createdAt = aCreatedAt;
        this.updatedAt = anUpdatedAt;
    }

    //Used to create an instance of ActivityJpaEntity from an instance of Activity.
    public static  ActivityJpaEntity from(final Activity anActivity){

        final var aType = List.of(Type.values()).stream()
                .filter(a -> a.equals(anActivity.getType()))
                .findFirst()
                .get()
                .ordinal();

        return new ActivityJpaEntity(anActivity.getId(),
                anActivity.getDescription(),
                anActivity.getDate(),
                anActivity.getValue(),
                aType,
                anActivity.getCreatedAt(),
                anActivity.getUpdatedAt());
    }

    //Used to create an Activity instance from an ActivityJpaEntity instance.
    public Activity toModel(){
        return Activity.with(this.getId(),
                this.getDate(),
                this.getDescription(),
                this.getValue(),
                Type.values()[this.getType()],
                this.getCreatedAt(),
                this.getUpdatedAt());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
