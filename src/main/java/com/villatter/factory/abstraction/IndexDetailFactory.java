package com.villatter.factory.abstraction;

public interface IndexDetailFactory extends IndexFactory{
    public Object getParent();
    public void setParentId(Object parentId);
    public Object getParentId();
}
