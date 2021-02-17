package com.conference.my.model.dao.util;

public interface Converter<E,V> {
  E convert(V value);
}
