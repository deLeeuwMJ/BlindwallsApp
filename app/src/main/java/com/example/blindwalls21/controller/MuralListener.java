package com.example.blindwalls21.controller;

import com.example.blindwalls21.model.Mural;

public interface MuralListener {

    void onMuralAvailable(Mural mural);
    void onMuralError(Error error);
}
