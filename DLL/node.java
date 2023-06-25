/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DLL;

/**
 *
 * @author Ahmed
 */
public class node {
    public Object data;
    public node next;
    public node prev;

    public node(Object data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
    
}
