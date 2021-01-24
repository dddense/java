package ru.itis.download.utils;

public interface IStringConverter<T> {

	T convert(String value);
}