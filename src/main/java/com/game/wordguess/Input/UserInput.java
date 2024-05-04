package com.game.wordguess.Input;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserInput {
    @NotEmpty
    @Size(max = 1,message = "must provide one letter")
    private String input;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
