package br.com.cwi.apus.order.utils;

import java.util.Objects;

public class DomainUtils {

    public static Long toInternalId(String externalId) {
        return Long.parseLong(externalId);
    }

    public static String toExternalId(Long id) {
        return Objects.toString(id);
    }
}
