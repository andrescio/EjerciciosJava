package com.example.block16springcloud;

import com.example.block16springcloud.exception.UnprocessableEntityException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class Utils {

    // Method that receives a bindingResult, which is a list of errors that appears when trying to create or
    // modify an Entity. If there are any errors in the bindingResult, this method throws an Exception and shows
    // them.
    public static void showErrors(BindingResult bindingResult) {
        String errors = "";
        for(ObjectError e: bindingResult.getAllErrors()){
            errors +=((FieldError) e).getField() + " " + e.getDefaultMessage()+", ";
        }
        errors = StringUtils.chop(errors.trim()); // Delete the last character of a String. In this case a comma (,)
        if(!errors.equals(""))
            throw new UnprocessableEntityException(errors);
    }
}
