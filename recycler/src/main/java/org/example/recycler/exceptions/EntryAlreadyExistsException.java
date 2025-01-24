package org.example.recycler.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "Entry already exists.")
public class EntryAlreadyExistsException extends RuntimeException {
}
