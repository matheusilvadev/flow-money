package flowmoney.backend.domain.activity;

import flowmoney.backend.domain.exception.DomainException;
import flowmoney.backend.utils.InstantUtils;

import java.time.Instant;
import java.util.UUID;

public class Activity {

    private String id;
    private Instant date;
    private String description;
    private float value;
    private Type type;
    private Instant createdAt;
    private Instant updatedAt;

    //Main constructor, used internally to create instances of the class
    private Activity(final String anId, final Instant aDate,
                     final String aDescription, final float aValue,
                     final Type aType, final Instant aCreatedAt, final Instant anUpdatedAt){
        this.id = anId;
        this.date = aDate;
        this.description = aDescription;
        this.value = aValue;
        this.type = aType;
        this.createdAt = aCreatedAt;
        this.updatedAt = anUpdatedAt;

        this.validate();
    }

    //Acts as a factory method
    public static Activity newActivity(final Instant aDate, final String aDescription,
                                       final float aValue, final Type aType){
        return new Activity(
                UUID.randomUUID().toString().toLowerCase(),
                aDate,
                aDescription,
                aValue,
                aType,
                InstantUtils.now(),
                InstantUtils.now()
        );
    }

    //Used to retrieve data from the database or deserialize an object.
    public static Activity with(final String anId, final Instant aDate,
                                final String aDescription, final float aValue,
                                final Type aType, final Instant aCreatedAt, final Instant anUpdatedAt){
        return new Activity(anId, aDate, aDescription, aValue, aType, aCreatedAt, anUpdatedAt);
    }

    //Method that brings together all attribute validation methods
    private void validate(){
        validateId();
        validateDescription();
        validateType();
        validateValue();
        validateTimestamp();
    }

    private void validateId(){
        if (this.id.isBlank()){
            throw new DomainException("Activity's ID should not be blank");
        }
        if (this.id.length() != 36){
            throw new DomainException("Activity's ID should be a valid UUID");
        }
    }

    public void validateDescription(){
        if (this.description.isBlank()){
            throw new DomainException("Activity's DESCRIPTION should not be blank");
        }
        if (this.description.length() < 3){
            throw new DomainException("Activity's DESCRIPTION should have at least 3 characters");
        }
    }

    private void validateType(){
        if (this.type != Type.EXPENSE && this.type != Type.REVENUE){
            throw new DomainException("Activity's TYPE should be either expense or revenue");
        }
    }

    private void validateValue(){
        if (this.value < 0.01){
            throw new DomainException("Activity's VALUE should be greater than zero");
        }
    }

    private void validateTimestamp(){
        if (this.createdAt.isAfter(this.updatedAt)){
            throw new DomainException("Activity's CREATED AT should be before UPDATED AT");
        }
    }

    public String getId() {
        return id;
    }

    public Instant getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public float getValue() {
        return value;
    }

    public Type getType() {
        return type;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", value=" + value +
                ", type=" + type +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
