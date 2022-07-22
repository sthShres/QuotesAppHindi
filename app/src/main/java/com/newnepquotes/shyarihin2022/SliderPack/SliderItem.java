package com.newnepquotes.shyarihin2022.SliderPack;

public class SliderItem {
    private int image;
    //Here you can use String variable to store url
    // if you want ot load image form the inernet
    public SliderItem(int image){
        this.image = image;
    }
    public int getImage(){
        return image;
    }
}
