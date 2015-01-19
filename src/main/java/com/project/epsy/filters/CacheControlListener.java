/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.epsy.filters;


import java.util.logging.Level;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class CacheControlListener implements PhaseListener
{
    public PhaseId getPhaseId()
    {
        return PhaseId.RENDER_RESPONSE;
    }
 
    @Override
    public void afterPhase(PhaseEvent event)
    {
    }
 
    @Override
    public void beforePhase(PhaseEvent event)
    {
      java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Phase Listener");
        FacesContext facesContext = event.getFacesContext();
        HttpServletResponse response = (HttpServletResponse) facesContext
                .getExternalContext().getResponse();
        HttpServletRequest req = (HttpServletRequest) facesContext
                .getExternalContext().getRequest();
       if(req.getRequestURI().contains("index.xhtml")){
            java.util.logging.Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,"Start Page Hit..Header is going to be manipulated..");
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        // Stronger according to blog comment below that references HTTP spec
        response.addHeader("Cache-Control", "no-store");
        response.addHeader("Cache-Control", "must-revalidate");
        // some date in the past
        response.addHeader("Expires", "Mon, 8 Aug 2006 10:00:00 GMT");
        //response.sendRedirect(req.getContextPath()+"/");
   }
    }
}