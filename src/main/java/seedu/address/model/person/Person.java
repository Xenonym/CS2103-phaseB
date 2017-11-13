package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import seedu.address.logic.parser.ParserUtil.Option;
import seedu.address.model.relationship.Relationship;
import seedu.address.model.relationship.RelationshipDirection;
import seedu.address.model.relationship.UniqueRelationshipList;

import seedu.address.model.relationship.exceptions.DuplicateRelationshipException;

import seedu.address.model.tag.Tag;
import seedu.address.model.tag.UniqueTagList;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Person implements ReadOnlyPerson, Comparable<Person> {

    private ObjectProperty<Name> name;
    private ObjectProperty<Phone> phone;
    private ObjectProperty<Email> email;
    private ObjectProperty<Address> address;
    private ObjectProperty<Remark> remark;

    private ObjectProperty<UniqueTagList> tags;
    private ObjectProperty<UniqueRelationshipList> relationships;

    private Option sortOption;
    //@@ author wenmogu
    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags,
                  Set<Relationship> relationships) {
        requireAllNonNull(name, phone, email, address, remark, tags, relationships);
        this.name = new SimpleObjectProperty<>(name);
        this.phone = new SimpleObjectProperty<>(phone);
        this.email = new SimpleObjectProperty<>(email);
        this.address = new SimpleObjectProperty<>(address);
        this.remark = new SimpleObjectProperty<>(remark);
        // protect internal tags from changes in the arg list
        this.tags = new SimpleObjectProperty<>(new UniqueTagList(tags));
        // protected internal relationships from changes in the arg list
        this.relationships = new SimpleObjectProperty<>(new UniqueRelationshipList(relationships));
    }

    /**
     * For initial construction of a Person.
     */
    public Person(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags) {
        this(name, phone, email, address, remark, tags, new HashSet<>());
    }

    /**
     * Creates a copy of the given ReadOnlyPerson.
     */
    public Person(ReadOnlyPerson source) {
        this(source.getName(), source.getPhone(), source.getEmail(), source.getAddress(), source.getRemark(),
                source.getTags(), source.getRelationships());
    }

    public void setName(Name name) {
        this.name.set(requireNonNull(name));
    }

    @Override
    public ObjectProperty<Name> nameProperty() {
        return name;
    }

    @Override
    public Name getName() {
        return name.get();
    }

    public void setPhone(Phone phone) {
        this.phone.set(requireNonNull(phone));
    }

    @Override
    public ObjectProperty<Phone> phoneProperty() {
        return phone;
    }

    @Override
    public Phone getPhone() {
        return phone.get();
    }

    public void setEmail(Email email) {
        this.email.set(requireNonNull(email));
    }

    @Override
    public ObjectProperty<Email> emailProperty() {
        return email;
    }

    @Override
    public Email getEmail() {
        return email.get();
    }

    public void setAddress(Address address) {
        this.address.set(requireNonNull(address));
    }

    @Override
    public ObjectProperty<Address> addressProperty() {
        return address;
    }

    @Override
    public Address getAddress() {
        return address.get();
    }

    public void setRemark(Remark remark) {
        this.remark.set(requireNonNull(remark));
    }

    @Override
    public ObjectProperty<Remark> remarkProperty() {
        return remark;
    }

    @Override
    public Remark getRemark() {
        return remark.get();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    @Override
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags.get().toSet());
    }

    @Override
    public ObjectProperty<UniqueTagList> tagProperty() {
        return tags;
    }

    /**
     * Replaces this person's tags with the tags in the argument tag set.
     */
    public void setTags(Set<Tag> replacement) {
        tags.set(new UniqueTagList(replacement));
    }

    //@@ author wenmogu
    /**
     * Remove a tag from the person's tag list.
     */
    public boolean removeTag(Tag tagGettingRemoved) {
        UniqueTagList tagsList = tags.get();
        return tagsList.removeTag(tagGettingRemoved);
    }

    //@@ author wenmogu
    @Override
    public Set<Relationship> getRelationships() {
        return Collections.unmodifiableSet(relationships.get().toSet());
    }

    //@@ author wenmogu
    /**
     * Add a relationship to a person's relationships
     */
    public boolean addRelationship(Relationship re) throws DuplicateRelationshipException {
        UniqueRelationshipList reList = relationships.get();
        ArrayList<Relationship> oppoRelationships = re.oppositeRelationships();
        for (Relationship oppoRe: oppoRelationships) {
            removeRelationship(oppoRe);
        }

        if (!re.isUndirected()) {
            Relationship directlyOppoRelationship = new Relationship(re.getToPerson(), re.getFromPerson(),
                    RelationshipDirection.DIRECTED);
            removeRelationship(directlyOppoRelationship);
        }
        reList.add(re);

        return true;
    }

    //@@author wenmogu
    /**
     * Removes a relationship from a person's relationships
     */
    public boolean removeRelationship(Relationship re) {
        UniqueRelationshipList reList = relationships.get();
        return reList.removeRelationship(re);
    }

    //@@author

    //@@author TanYikai
    /**
     * Sets the sort option using integer
     * 0,1,2,3,4 represents name, phone, email, address and remark respectively
     */
    public void setSortOption(Option option) {
        this.sortOption = option;
    }
    //@@author
    @Override
    public ReadOnlyPerson copy() {
        Name name = this.getName();
        Phone phone = this.getPhone();
        Email email = this.getEmail();
        Address address = this.getAddress();
        Remark remark = this.getRemark();
        Set<Tag> tags = this.getTags();
        Set<Relationship> relationships = this.getRelationships();
        return new Person(name, phone, email, address, remark, tags, relationships);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyPerson // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyPerson) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, remark, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }

    //@@author TanYikai
    /**
     * The compareTo method compares different attribute of the person object according to sortOption number
     * The default is compare by name
     */
    @Override
    public int compareTo(Person o) {
        if (sortOption == Option.PHONE) {
            return this.getPhone().toString().compareToIgnoreCase(o.getPhone().toString());
        } else if (sortOption == Option.EMAIL) {
            return this.getEmail().toString().compareToIgnoreCase(o.getEmail().toString());
        } else if (sortOption == Option.ADDRESS) {
            return this.getAddress().toString().compareToIgnoreCase(o.getAddress().toString());
        } else if (sortOption == Option.REMARK) {
            return this.getRemark().toString().compareToIgnoreCase(o.getRemark().toString());
        }
        return this.getName().toString().compareToIgnoreCase(o.getName().toString());
    }
    //@@author
}
