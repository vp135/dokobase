package base.doko;

import base.Card;
import base.Logger;
import base.Statics;
import base.doko.messages.MessageGameSelected;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Compare {

    private final static Logger log = new Logger(Compare.class.getName(),1);


    public static Card getWinner(Card card1, Card card2, MessageGameSelected.GAMES game, boolean schwein){
        switch (game){
            case NORMAL:
            case KARO:
            case ARMUT:
            case HOCHZEIT:
            case KOENIGE:
                return normalGame(card1,card2,schwein);
            case DAMEN:
                return damen(card1,card2);
            case BUBEN:
                return buben(card1,card2);
            case BUBENDAMEN:
                return bubendamen(card1,card2);
            case FLEISCHLOS:
                return fleischlos(card1,card2);
            case KREUZ:
                return kreuz(card1,card2);
            case PIK:
                return pik(card1,card2);
            case HERZ:
                return herz(card1,card2);
            default:
                return card1;
        }
    }


    public static Card normalGame2 (Card first, Card second, boolean schwein, MessageGameSelected.GAMES game){
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

    private static Card getWinnerForSameColor(Card first, Card second, MessageGameSelected.GAMES game) {
        List<Card> list = new ArrayList<>();
        //DokoCards.createCardListBySuit(first.suit, game);
        return null;
    }


    public static Card normalGame (Card first, Card second, boolean schwein) {
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

    public static Card damen(Card first, Card second) {
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

    public static Card buben(Card first, Card second) {
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

    public static Card bubendamen(Card first, Card second) {
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

    public static Card fleischlos(Card first, Card second) {
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

    public static Card kreuz(Card first, Card second) {
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

    public static Card pik(Card first, Card second) {
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

    public static Card herz(Card first, Card second) {
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

    public static Comparator<Card> getComparer(MessageGameSelected.GAMES game, boolean schwein) {
        return (o1, o2) -> {
            Card winner = getWinner(o1, o2, game, schwein);
            if (o1.kind.equals(o2.kind) && o1.suit.equals(o2.suit)) {
                return 0;
            } else if (winner.suit.equals(o1.suit) && winner.kind.equals(o1.kind)) {
                return 1;
            } else if (winner.suit.equals(o2.suit) && winner.kind.equals(o2.kind)) {
                return -1;
            }
            else{
                return 1;
            }
        };
    }
}
