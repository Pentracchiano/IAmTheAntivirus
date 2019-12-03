package controllers.game;

import views.game.View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gerardo
 */
public abstract class Controller {
    
    protected View view;

    public Controller(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }
    
}
