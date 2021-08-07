package base.doko;

import base.BaseCard;
import base.Logger;
import base.Statics;
import base.doko.messages.MessageGameSelected;

import java.util.ArrayList;
import java.util.List;

public class Compare {

    private final static Logger log = new Logger(Compare.class.getName(),1);

    public static BaseCard normalGame2 (BaseCard first, BaseCard second, boolean schwein, MessageGameSelected.GAMES game){
        if (first.trump && !second.trump){
            return first;
        }
        else if(!first.trump && second.trump){
            return second;
        }
        else if(!first.trump){
            if(first.suit.equals(second.suit)){
                return first;
            }
            else{
                return getWinnerForSameColor(first,second,game);
            }
        }
        else{
            //TODO
            //return getWinnerTrumpf(first,second,schwein);
            return first;
        }
    }

    private static BaseCard getWinnerForSameColor(BaseCard first, BaseCard second, MessageGameSelected.GAMES game) {
        List<BaseCard> list = new ArrayList<>();
        DokoCards.createCardListBySuit(first.suit, game);
        return null;
    }

    public static BaseCard normalGame (BaseCard first, BaseCard second, boolean schwein) {
        switch (first.suit) {
            case Statics.KREUZ: {
                switch (first.kind){
                    case Statics.ZEHN:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.KARO)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if(second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.KARO)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.KARO)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.PIK: {
                switch (first.kind){
                    case Statics.ZEHN:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.KARO)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.kind.equals(Statics.BUBE)&&second.suit.equals(Statics.KREUZ))
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.kind.equals(Statics.DAME)&& second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.KARO)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.PIK))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.KARO)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.HERZ: {
                switch (first.kind){
                    case Statics.ZEHN:{
                        if((second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.kind.equals(Statics.BUBE)&&(second.suit.equals(Statics.PIK)||second.suit.equals(Statics.KREUZ)))
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.kind.equals(Statics.DAME)&&(second.suit.equals(Statics.PIK)||second.suit.equals(Statics.KREUZ)))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || (second.suit.equals(Statics.KARO)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.HERZ)))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || (second.suit.equals(Statics.KARO)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ)))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.KARO: {
                switch (first.kind){
                    case Statics.ZEHN:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.kind.equals(Statics.BUBE)&&!second.suit.equals(Statics.KARO))
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.kind.equals(Statics.DAME)&&!second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(schwein){
                            return first;
                        }
                        else{
                            if(second.kind.equals(Statics.BUBE)
                                    || second.kind.equals(Statics.DAME)
                                    || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                                return second;
                            }else {
                                return first;
                            }
                        }
                    }
                }
                break;
            }

        }
        log.error("Error");
        return second;
    }

    public static BaseCard damen(BaseCard first, BaseCard second) {
        //System.out.println(first.toString() +":"+ second.toString());
        switch (first.suit) {
            case Statics.KREUZ: {
                switch (first.kind){
                    case Statics.DAME: {
                        return first;
                    }
                    case Statics.ASS:{
                        if(second.kind.equals(Statics.DAME)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if (second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.KREUZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if (second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.KOENIG) && second.suit.equals(Statics.KREUZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.PIK: {
                switch (first.kind){
                    case Statics.DAME: {
                        if(second.kind.equals(Statics.DAME)&&second.suit.equals(Statics.KREUZ)){
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.kind.equals(Statics.DAME)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if (second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.PIK))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.PIK))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if (second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.PIK))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.PIK))
                                || (second.kind.equals(Statics.KOENIG) && second.suit.equals(Statics.PIK))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.HERZ: {
                switch (first.kind){
                    case Statics.DAME: {
                        if((second.kind.equals(Statics.DAME)&&second.suit.equals(Statics.KREUZ))
                        ||(second.kind.equals(Statics.DAME)&&second.suit.equals(Statics.PIK))){
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.kind.equals(Statics.DAME)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if (second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.HERZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if (second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.KOENIG) && second.suit.equals(Statics.HERZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.KARO: {
                switch (first.kind){
                    case Statics.DAME: {
                        if((second.kind.equals(Statics.DAME)&&!second.suit.equals(Statics.KARO))){
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.kind.equals(Statics.DAME)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KARO))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if (second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.KARO))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if (second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.KOENIG) && second.suit.equals(Statics.KARO))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }

        }
        log.error("Error");
        return second;
    }

    public static BaseCard buben(BaseCard first, BaseCard second) {
        //System.out.println(first.toString() +":"+ second.toString());
        switch (first.suit) {
            case Statics.KREUZ: {
                switch (first.kind){
                    case Statics.BUBE: {
                        return first;
                    }
                    case Statics.ASS:{
                        if(second.kind.equals(Statics.BUBE)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.kind.equals(Statics.BUBE)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if (second.kind.equals(Statics.BUBE)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.KREUZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{
                        if (second.kind.equals(Statics.BUBE)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.KOENIG) && second.suit.equals(Statics.KREUZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.PIK: {
                switch (first.kind){
                    case Statics.BUBE: {
                        if(second.kind.equals(Statics.BUBE)&&second.suit.equals(Statics.KREUZ)){
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.kind.equals(Statics.BUBE)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.kind.equals(Statics.BUBE)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if (second.kind.equals(Statics.BUBE)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.PIK))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.PIK))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{
                        if (second.kind.equals(Statics.BUBE)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.PIK))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.PIK))
                                || (second.kind.equals(Statics.KOENIG) && second.suit.equals(Statics.PIK))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.HERZ: {
                switch (first.kind){
                    case Statics.BUBE: {
                        if((second.kind.equals(Statics.BUBE)&&second.suit.equals(Statics.KREUZ))
                                ||(second.kind.equals(Statics.BUBE)&&second.suit.equals(Statics.PIK))){
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.kind.equals(Statics.BUBE)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.kind.equals(Statics.BUBE)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if (second.kind.equals(Statics.BUBE)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.HERZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{
                        if (second.kind.equals(Statics.BUBE)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.KOENIG) && second.suit.equals(Statics.HERZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.KARO: {
                switch (first.kind){
                    case Statics.BUBE: {
                        if((second.kind.equals(Statics.BUBE)&&!second.suit.equals(Statics.KARO))){
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.kind.equals(Statics.BUBE)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.kind.equals(Statics.BUBE)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KARO))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if (second.kind.equals(Statics.BUBE)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.KARO))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{
                        if (second.kind.equals(Statics.BUBE)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.KOENIG) && second.suit.equals(Statics.KARO))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }

        }
        log.error("Error");
        return second;
    }

    public static BaseCard bubendamen(BaseCard first, BaseCard second) {
        //System.out.println(first.toString() + ":" + second.toString());
        switch (first.suit) {
            case Statics.KREUZ: {
                switch (first.kind) {
                    case Statics.DAME: {
                        return first;
                    }
                    case Statics.BUBE: {
                        if (second.kind.equals(Statics.DAME)) {
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case Statics.ASS: {
                        if (second.kind.equals(Statics.BUBE) || second.kind.equals(Statics.DAME)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.kind.equals(Statics.BUBE) || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG: {
                        if (second.kind.equals(Statics.BUBE) || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.PIK: {
                switch (first.kind) {
                    case Statics.DAME: {
                        if (second.kind.equals(Statics.DAME) && second.suit.equals(Statics.KREUZ)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.BUBE: {
                        if (second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.BUBE) && second.suit.equals(Statics.KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.ASS: {
                        if (second.kind.equals(Statics.BUBE) || second.kind.equals(Statics.DAME)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.kind.equals(Statics.BUBE) || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG: {
                        if (second.kind.equals(Statics.BUBE) || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.PIK))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.HERZ: {
                switch (first.kind) {
                    case Statics.DAME: {
                        if ((second.kind.equals(Statics.DAME) && second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.DAME) && second.suit.equals(Statics.PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.BUBE: {
                        if (second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.BUBE) && second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.BUBE) && second.suit.equals(Statics.PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.ASS: {
                        if (second.kind.equals(Statics.BUBE) || second.kind.equals(Statics.DAME)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.kind.equals(Statics.BUBE) || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG: {
                        if (second.kind.equals(Statics.BUBE) || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.KARO: {
                switch (first.kind) {
                    case Statics.DAME: {
                        if (second.kind.equals(Statics.DAME) && !second.suit.equals(Statics.KARO)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.BUBE: {
                        if (second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.BUBE) && !second.suit.equals(Statics.KARO))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.ASS: {
                        if (second.kind.equals(Statics.BUBE) || second.kind.equals(Statics.DAME)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.kind.equals(Statics.BUBE) || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KARO))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG: {
                        if (second.kind.equals(Statics.BUBE) || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.KARO))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                }
                break;
            }

        }
        log.error("Error");
        return second;
    }

    public static BaseCard fleischlos(BaseCard first, BaseCard second) {
        //System.out.println(first.toString() + ":" + second.toString());
        switch (first.suit) {
            case Statics.KREUZ: {
                switch (first.kind) {
                    case Statics.ASS: {
                        return first;
                    }
                    case Statics.ZEHN: {
                        if (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KREUZ)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG: {
                        if ((second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.DAME: {
                        if ((second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.KOENIG) && second.suit.equals(Statics.KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.BUBE: {
                        if ((second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.KOENIG) && second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.DAME) && second.suit.equals(Statics.KREUZ))) {
                            return second;
                        }
                        else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.PIK: {
                switch (first.kind) {
                    case Statics.ASS: {
                        return first;
                    }
                    case Statics.ZEHN: {
                        if (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.PIK)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG: {
                        if ((second.kind.equals(Statics.ASS) && second.suit.equals(Statics.PIK))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.DAME: {
                        if ((second.kind.equals(Statics.ASS) && second.suit.equals(Statics.PIK))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.PIK))
                                || (second.kind.equals(Statics.KOENIG) && second.suit.equals(Statics.PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.BUBE: {
                        if ((second.kind.equals(Statics.ASS) && second.suit.equals(Statics.PIK))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.PIK))
                                || (second.kind.equals(Statics.KOENIG) && second.suit.equals(Statics.PIK))
                                || (second.kind.equals(Statics.DAME) && second.suit.equals(Statics.PIK))) {
                            return second;
                        }
                        else {
                            return first;
                        }
                    }
                }
                break;

            }
            case Statics.HERZ: {
                switch (first.kind) {
                    case Statics.ASS: {
                        return first;
                    }
                    case Statics.ZEHN: {
                        if (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.HERZ)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG: {
                        if ((second.kind.equals(Statics.ASS) && second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.DAME: {
                        if ((second.kind.equals(Statics.ASS) && second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.KOENIG) && second.suit.equals(Statics.HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.BUBE: {
                        if ((second.kind.equals(Statics.ASS) && second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.KOENIG) && second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.DAME) && second.suit.equals(Statics.HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.KARO: {
                switch (first.kind) {
                    case Statics.ASS: {
                        return first;
                    }
                    case Statics.ZEHN: {
                        if (second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KARO)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG: {
                        if ((second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.KARO))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.DAME: {
                        if ((second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.KOENIG) && second.suit.equals(Statics.KARO))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.BUBE: {
                        if ((second.kind.equals(Statics.ASS) && second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.KOENIG) && second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.DAME) && second.suit.equals(Statics.KARO))) {
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                }
                break;
            }
        }
        log.error("Error");
        return second;
    }

    public static BaseCard kreuz(BaseCard first, BaseCard second) {
        //System.out.println(first.toString() +":"+ second.toString());
        switch (first.suit) {
            case Statics.KREUZ: {
                switch (first.kind){
                    case Statics.ZEHN:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if(second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.PIK: {
                switch (first.kind){
                    case Statics.ZEHN:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.KREUZ)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.kind.equals(Statics.BUBE)&&second.suit.equals(Statics.KREUZ))
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.kind.equals(Statics.DAME)&& second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.KREUZ)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.PIK))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.KREUZ)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.HERZ: {
                switch (first.kind){
                    case Statics.ZEHN:{
                        if((second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.kind.equals(Statics.BUBE)&&(second.suit.equals(Statics.PIK)||second.suit.equals(Statics.KREUZ)))
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{
                        if((second.kind.equals(Statics.DAME)&&(second.suit.equals(Statics.PIK)||second.suit.equals(Statics.KREUZ)))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.KREUZ)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.KREUZ)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.KARO: {
                switch (first.kind){
                    case Statics.ZEHN:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.KREUZ)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.kind.equals(Statics.BUBE)&&!second.suit.equals(Statics.KARO))
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.kind.equals(Statics.DAME)&&!second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.KREUZ)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS: {
                        if (second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.KREUZ)
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                }
                break;
            }

        }
        log.error("Error");
        return second;
    }

    public static BaseCard pik(BaseCard first, BaseCard second) {
        //System.out.println(first.toString() +":"+ second.toString());
        switch (first.suit) {
            case Statics.KREUZ: {
                switch (first.kind){
                    case Statics.ZEHN:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.PIK)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if(second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.PIK)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.PIK)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.PIK: {
                switch (first.kind){
                    case Statics.ZEHN:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.kind.equals(Statics.BUBE)&&second.suit.equals(Statics.KREUZ))
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.kind.equals(Statics.DAME)&& second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.PIK))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.HERZ: {
                switch (first.kind){
                    case Statics.ZEHN:{
                        if((second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.kind.equals(Statics.BUBE)&&(second.suit.equals(Statics.PIK)||second.suit.equals(Statics.KREUZ)))
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{
                        if((second.kind.equals(Statics.DAME)&&(second.suit.equals(Statics.PIK)||second.suit.equals(Statics.KREUZ)))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.PIK)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.PIK)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.KARO: {
                switch (first.kind){
                    case Statics.ZEHN:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.PIK)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.kind.equals(Statics.BUBE)&&!second.suit.equals(Statics.KARO))
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.kind.equals(Statics.DAME)&&!second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.PIK)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS: {
                        if (second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.PIK)
                                || (second.kind.equals(Statics.ZEHN) && second.suit.equals(Statics.HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                }
                break;
            }

        }
        log.error("Error");
        return second;
    }

    public static BaseCard herz(BaseCard first, BaseCard second) {
        //System.out.println(first.toString() +":"+ second.toString());
        switch (first.suit) {
            case Statics.KREUZ: {
                switch (first.kind){
                    case Statics.ZEHN:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.HERZ)
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if(second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{
                        if((second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.HERZ)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.HERZ)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.PIK: {
                switch (first.kind){
                    case Statics.ZEHN:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.HERZ)
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.kind.equals(Statics.BUBE)&&second.suit.equals(Statics.KREUZ))
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.kind.equals(Statics.DAME)&& second.suit.equals(Statics.KREUZ))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.HERZ)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.PIK))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.HERZ)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.HERZ: {
                switch (first.kind){
                    case Statics.ZEHN:{
                        if((second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.kind.equals(Statics.BUBE)&&(second.suit.equals(Statics.PIK)||second.suit.equals(Statics.KREUZ)))
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{
                        if((second.kind.equals(Statics.DAME)&&(second.suit.equals(Statics.PIK)||second.suit.equals(Statics.KREUZ)))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ)
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.HERZ)))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.KARO: {
                switch (first.kind){
                    case Statics.ZEHN:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.HERZ)
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.kind.equals(Statics.BUBE)&&!second.suit.equals(Statics.KARO))
                                || second.kind.equals(Statics.DAME)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.kind.equals(Statics.DAME)&&!second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.HERZ)
                                || (second.kind.equals(Statics.ZEHN)&&second.suit.equals(Statics.KARO))
                                || (second.kind.equals(Statics.ASS)&&second.suit.equals(Statics.KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS: {
                        if (second.kind.equals(Statics.BUBE)
                                || second.kind.equals(Statics.DAME)
                                || second.suit.equals(Statics.HERZ)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                }
                break;
            }
        }
        log.error("Error");
        return second;
    }

}
