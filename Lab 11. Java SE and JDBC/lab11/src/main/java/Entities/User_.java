package Entities;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-11-16T18:58:51", comments="EclipseLink-4.0.2.v20230616-r3bfa6ac6ddf76d7909adc5ea7ecaa47c02c007ed")
@StaticMetamodel(User.class)
@SuppressWarnings({"rawtypes", "deprecation"})
public class User_ {

    public static volatile SingularAttribute<User, String> login;
    public static volatile SingularAttribute<User, String> hash;
    public static volatile SingularAttribute<User, Boolean> isAdmin;

}
