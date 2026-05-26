package Utils;

import Model.Referent;

public class Concatenation {

    public static String concatenate(Referent referent) {
        StringBuilder stringB = new StringBuilder();
        stringB.append(referent.getId().toString());
        stringB.append(") ");
        stringB.append(referent.getDesignation());
        stringB.append(" ");
        stringB.append(referent.getFirstName());
        stringB.append(" ");
        stringB.append(referent.getLastName());
        stringB.append(", ");
        stringB.append(
            (referent.getNickname() == null
                ? ""
                : referent.getNickname() + ", ")
        );
        stringB.append("born the ");
        stringB.append(referent.getBirthDate().toString());
        stringB.append((referent.getIsAlive()) ? ", alive" : ", dead");
        stringB.append(
            (referent.getWebsite()) == null ? "" : ", " + referent.getWebsite()
        );
        return stringB.toString();
    }
}
