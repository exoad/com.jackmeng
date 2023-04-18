// Copyright 2023 Jack Meng. All rights reserved.
// Use of this source code is governed by a BSD-style
// license that can be found in the LICENSE file.

package com.jackmeng.stl;

public class stl_CircularList< T >
{
  private Node< T > head;
  private Node< T > tail;

  private static class Node< T >
  {
    T data;
    Node< T > next;

    public Node(T data)
    {
      this.data = data;
    }
  }

  public stl_CircularList()
  {
    head = null;
    tail = null;
  }

  public void add(T data)
  {
    Node< T > newNode = new Node<>(data);
    if (head == null)
    {
      head = newNode;
      tail = newNode;
      newNode.next = head;
    }
    else
    {
      tail.next = newNode;
      tail = newNode;
      tail.next = head;
    }
  }

  public boolean isEmpty()
  {
    return head == null;
  }

  public int size()
  {
    int size = 0;
    Node< T > currentNode = head;
    do
    {
      size++;
      currentNode = currentNode.next;
    }
    while (currentNode != head);
    return size;
  }

  public T get(int index)
  {
    Node< T > currentNode = head;
    int currentIndex = 0;
    while (currentIndex < index)
    {
      currentNode = currentNode.next;
      currentIndex++;
    }
    return currentNode.data;
  }

  public void remove(int index)
  {
    if (head == null)
      return;
    if (index == 0)
    {
      if (head == tail)
      {
        head = null;
        tail = null;
      }
      else
      {
        head = head.next;
        tail.next = head;
      }
      return;
    }
    Node< T > currentNode = head;
    int currentIndex = 0;
    while (currentIndex < index - 1)
    {
      currentNode = currentNode.next;
      currentIndex++;
    }
    currentNode.next = currentNode.next.next;
    if (currentNode.next == null)
      tail = currentNode;

    }
}