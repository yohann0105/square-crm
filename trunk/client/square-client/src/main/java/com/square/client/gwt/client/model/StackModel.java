/*
 * This file is a part of Square, Customer Relationship Management Software for insurance's companies
 * Copyright (C) 2010-2012  SCUB <square@scub.net> - Mutuelle SMATIS FRANCE  <square@smatis.fr >
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2 of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
/**
 * 
 */
package com.square.client.gwt.client.model;

import java.util.ArrayList;
import java.util.EmptyStackException;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * Implementation Stack Gwt.
 * @author Goumard Stephane (scub). - SCUB
 */
public class StackModel<E> extends ArrayList<E> implements IsSerializable {
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 8134831964893043091L;

    /**
     * Constructs a stack with the default size of {@code Vector}.
     */
    public StackModel() {
        super();
    }

    /**
     * Returns whether the stack is empty or not.
     * @return {@code true} if the stack is empty, {@code false} otherwise.
     */
    public boolean empty() {
        return this.isEmpty();
    }

    /**
     * Returns the element at the top of the stack without removing it.
     * @return the element at the top of the stack.
     * @throws EmptyStackException if the stack is empty.
     * @see #pop
     */
    public synchronized E peek() {
        try {
            return (E) this.get(this.size() - 1);
        }
        catch (IndexOutOfBoundsException e) {
            throw new EmptyStackException();
        }
    }

    /**
     * Returns the element at the top of the stack and removes it.
     * @return the element at the top of the stack.
     * @throws EmptyStackException if the stack is empty.
     * @see #peek
     * @see #push
     */
    public synchronized E pop() {
        final E object = peek();
        this.remove(object);
        return object;
    }

    /**
     * Pushes the specified object onto the top of the stack.
     * @param object The object to be added on top of the stack.
     * @return the object argument.
     * @see #peek
     * @see #pop
     */
    public E push(E object) {
        this.add(object);
        return object;
    }
}
