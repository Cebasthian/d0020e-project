package org.example.recycler.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "No such instructions entry.")
public class NoSuchEntryException extends RuntimeException {
}
