package base.doko;

import base.Logger;
import base.Statics;

public class Compare {

    private final static Logger log = new Logger(Compare.class.getName(),1);
    
    public static Card normalGame (Card first, Card second, boolean schwein) {
        switch (first.farbe) {
            case Statics.KREUZ: {
                switch (first.value){
                    case Statics.ZEHN:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.KARO)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if(second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.KARO)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.KARO)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.PIK: {
                switch (first.value){
                    case Statics.ZEHN:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.KARO)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.value.equals(Statics.BUBE)&&second.farbe.equals(Statics.KREUZ))
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.value.equals(Statics.DAME)&& second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.KARO)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.PIK))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.KARO)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.HERZ: {
                switch (first.value){
                    case Statics.ZEHN:{
                        if((second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.value.equals(Statics.BUBE)&&(second.farbe.equals(Statics.PIK)||second.farbe.equals(Statics.KREUZ)))
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.value.equals(Statics.DAME)&&(second.farbe.equals(Statics.PIK)||second.farbe.equals(Statics.KREUZ)))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || (second.farbe.equals(Statics.KARO)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.HERZ)))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || (second.farbe.equals(Statics.KARO)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ)))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.KARO: {
                switch (first.value){
                    case Statics.ZEHN:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.value.equals(Statics.BUBE)&&!second.farbe.equals(Statics.KARO))
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.value.equals(Statics.DAME)&&!second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KARO))){
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
                            if(second.value.equals(Statics.BUBE)
                                    || second.value.equals(Statics.DAME)
                                    || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
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
        switch (first.farbe) {
            case Statics.KREUZ: {
                switch (first.value){
                    case Statics.DAME: {
                        return first;
                    }
                    case Statics.ASS:{
                        if(second.value.equals(Statics.DAME)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if (second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.KREUZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if (second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.KOENIG) && second.farbe.equals(Statics.KREUZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.PIK: {
                switch (first.value){
                    case Statics.DAME: {
                        if(second.value.equals(Statics.DAME)&&second.farbe.equals(Statics.KREUZ)){
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.value.equals(Statics.DAME)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if (second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.PIK))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.PIK))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if (second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.PIK))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.PIK))
                                || (second.value.equals(Statics.KOENIG) && second.farbe.equals(Statics.PIK))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.HERZ: {
                switch (first.value){
                    case Statics.DAME: {
                        if((second.value.equals(Statics.DAME)&&second.farbe.equals(Statics.KREUZ))
                        ||(second.value.equals(Statics.DAME)&&second.farbe.equals(Statics.PIK))){
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.value.equals(Statics.DAME)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if (second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.HERZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if (second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.KOENIG) && second.farbe.equals(Statics.HERZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.KARO: {
                switch (first.value){
                    case Statics.DAME: {
                        if((second.value.equals(Statics.DAME)&&!second.farbe.equals(Statics.KARO))){
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.value.equals(Statics.DAME)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KARO))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if (second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.KARO))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if (second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.KOENIG) && second.farbe.equals(Statics.KARO))) {
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
        switch (first.farbe) {
            case Statics.KREUZ: {
                switch (first.value){
                    case Statics.BUBE: {
                        return first;
                    }
                    case Statics.ASS:{
                        if(second.value.equals(Statics.BUBE)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.value.equals(Statics.BUBE)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if (second.value.equals(Statics.BUBE)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.KREUZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{
                        if (second.value.equals(Statics.BUBE)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.KOENIG) && second.farbe.equals(Statics.KREUZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.PIK: {
                switch (first.value){
                    case Statics.BUBE: {
                        if(second.value.equals(Statics.BUBE)&&second.farbe.equals(Statics.KREUZ)){
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.value.equals(Statics.BUBE)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.value.equals(Statics.BUBE)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if (second.value.equals(Statics.BUBE)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.PIK))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.PIK))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{
                        if (second.value.equals(Statics.BUBE)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.PIK))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.PIK))
                                || (second.value.equals(Statics.KOENIG) && second.farbe.equals(Statics.PIK))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.HERZ: {
                switch (first.value){
                    case Statics.BUBE: {
                        if((second.value.equals(Statics.BUBE)&&second.farbe.equals(Statics.KREUZ))
                                ||(second.value.equals(Statics.BUBE)&&second.farbe.equals(Statics.PIK))){
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.value.equals(Statics.BUBE)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.value.equals(Statics.BUBE)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if (second.value.equals(Statics.BUBE)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.HERZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{
                        if (second.value.equals(Statics.BUBE)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.KOENIG) && second.farbe.equals(Statics.HERZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.KARO: {
                switch (first.value){
                    case Statics.BUBE: {
                        if((second.value.equals(Statics.BUBE)&&!second.farbe.equals(Statics.KARO))){
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.value.equals(Statics.BUBE)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.value.equals(Statics.BUBE)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KARO))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if (second.value.equals(Statics.BUBE)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.KARO))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{
                        if (second.value.equals(Statics.BUBE)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.KOENIG) && second.farbe.equals(Statics.KARO))) {
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
        switch (first.farbe) {
            case Statics.KREUZ: {
                switch (first.value) {
                    case Statics.DAME: {
                        return first;
                    }
                    case Statics.BUBE: {
                        if (second.value.equals(Statics.DAME)) {
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case Statics.ASS: {
                        if (second.value.equals(Statics.BUBE) || second.value.equals(Statics.DAME)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.value.equals(Statics.BUBE) || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG: {
                        if (second.value.equals(Statics.BUBE) || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.PIK: {
                switch (first.value) {
                    case Statics.DAME: {
                        if (second.value.equals(Statics.DAME) && second.farbe.equals(Statics.KREUZ)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.BUBE: {
                        if (second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.BUBE) && second.farbe.equals(Statics.KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.ASS: {
                        if (second.value.equals(Statics.BUBE) || second.value.equals(Statics.DAME)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.value.equals(Statics.BUBE) || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG: {
                        if (second.value.equals(Statics.BUBE) || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.PIK))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.HERZ: {
                switch (first.value) {
                    case Statics.DAME: {
                        if ((second.value.equals(Statics.DAME) && second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.DAME) && second.farbe.equals(Statics.PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.BUBE: {
                        if (second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.BUBE) && second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.BUBE) && second.farbe.equals(Statics.PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.ASS: {
                        if (second.value.equals(Statics.BUBE) || second.value.equals(Statics.DAME)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.value.equals(Statics.BUBE) || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG: {
                        if (second.value.equals(Statics.BUBE) || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.KARO: {
                switch (first.value) {
                    case Statics.DAME: {
                        if (second.value.equals(Statics.DAME) && !second.farbe.equals(Statics.KARO)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.BUBE: {
                        if (second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.BUBE) && !second.farbe.equals(Statics.KARO))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.ASS: {
                        if (second.value.equals(Statics.BUBE) || second.value.equals(Statics.DAME)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.ZEHN: {
                        if (second.value.equals(Statics.BUBE) || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KARO))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG: {
                        if (second.value.equals(Statics.BUBE) || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.KARO))) {
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
        switch (first.farbe) {
            case Statics.KREUZ: {
                switch (first.value) {
                    case Statics.ASS: {
                        return first;
                    }
                    case Statics.ZEHN: {
                        if (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KREUZ)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG: {
                        if ((second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.DAME: {
                        if ((second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.KOENIG) && second.farbe.equals(Statics.KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.BUBE: {
                        if ((second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.KOENIG) && second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.DAME) && second.farbe.equals(Statics.KREUZ))) {
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
                switch (first.value) {
                    case Statics.ASS: {
                        return first;
                    }
                    case Statics.ZEHN: {
                        if (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.PIK)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG: {
                        if ((second.value.equals(Statics.ASS) && second.farbe.equals(Statics.PIK))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.DAME: {
                        if ((second.value.equals(Statics.ASS) && second.farbe.equals(Statics.PIK))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.PIK))
                                || (second.value.equals(Statics.KOENIG) && second.farbe.equals(Statics.PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.BUBE: {
                        if ((second.value.equals(Statics.ASS) && second.farbe.equals(Statics.PIK))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.PIK))
                                || (second.value.equals(Statics.KOENIG) && second.farbe.equals(Statics.PIK))
                                || (second.value.equals(Statics.DAME) && second.farbe.equals(Statics.PIK))) {
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
                switch (first.value) {
                    case Statics.ASS: {
                        return first;
                    }
                    case Statics.ZEHN: {
                        if (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.HERZ)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG: {
                        if ((second.value.equals(Statics.ASS) && second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.DAME: {
                        if ((second.value.equals(Statics.ASS) && second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.KOENIG) && second.farbe.equals(Statics.HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.BUBE: {
                        if ((second.value.equals(Statics.ASS) && second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.KOENIG) && second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.DAME) && second.farbe.equals(Statics.HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.KARO: {
                switch (first.value) {
                    case Statics.ASS: {
                        return first;
                    }
                    case Statics.ZEHN: {
                        if (second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KARO)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.KOENIG: {
                        if ((second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.KARO))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.DAME: {
                        if ((second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.KOENIG) && second.farbe.equals(Statics.KARO))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case Statics.BUBE: {
                        if ((second.value.equals(Statics.ASS) && second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.KOENIG) && second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.DAME) && second.farbe.equals(Statics.KARO))) {
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
        switch (first.farbe) {
            case Statics.KREUZ: {
                switch (first.value){
                    case Statics.ZEHN:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if(second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.PIK: {
                switch (first.value){
                    case Statics.ZEHN:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.KREUZ)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.value.equals(Statics.BUBE)&&second.farbe.equals(Statics.KREUZ))
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.value.equals(Statics.DAME)&& second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.KREUZ)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.PIK))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.KREUZ)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.HERZ: {
                switch (first.value){
                    case Statics.ZEHN:{
                        if((second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.value.equals(Statics.BUBE)&&(second.farbe.equals(Statics.PIK)||second.farbe.equals(Statics.KREUZ)))
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{
                        if((second.value.equals(Statics.DAME)&&(second.farbe.equals(Statics.PIK)||second.farbe.equals(Statics.KREUZ)))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.KREUZ)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.KREUZ)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.KARO: {
                switch (first.value){
                    case Statics.ZEHN:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.KREUZ)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.value.equals(Statics.BUBE)&&!second.farbe.equals(Statics.KARO))
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.value.equals(Statics.DAME)&&!second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.KREUZ)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS: {
                        if (second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.KREUZ)
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.HERZ))) {
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
        switch (first.farbe) {
            case Statics.KREUZ: {
                switch (first.value){
                    case Statics.ZEHN:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.PIK)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if(second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.PIK)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.PIK)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.PIK: {
                switch (first.value){
                    case Statics.ZEHN:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.value.equals(Statics.BUBE)&&second.farbe.equals(Statics.KREUZ))
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.value.equals(Statics.DAME)&& second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.PIK))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.HERZ: {
                switch (first.value){
                    case Statics.ZEHN:{
                        if((second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.value.equals(Statics.BUBE)&&(second.farbe.equals(Statics.PIK)||second.farbe.equals(Statics.KREUZ)))
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{
                        if((second.value.equals(Statics.DAME)&&(second.farbe.equals(Statics.PIK)||second.farbe.equals(Statics.KREUZ)))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.PIK)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.PIK)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.KARO: {
                switch (first.value){
                    case Statics.ZEHN:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.PIK)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.value.equals(Statics.BUBE)&&!second.farbe.equals(Statics.KARO))
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.value.equals(Statics.DAME)&&!second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.PIK)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS: {
                        if (second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.PIK)
                                || (second.value.equals(Statics.ZEHN) && second.farbe.equals(Statics.HERZ))) {
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
        switch (first.farbe) {
            case Statics.KREUZ: {
                switch (first.value){
                    case Statics.ZEHN:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.HERZ)
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if(second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{
                        if((second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.HERZ)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.HERZ)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.PIK: {
                switch (first.value){
                    case Statics.ZEHN:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.HERZ)
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.value.equals(Statics.BUBE)&&second.farbe.equals(Statics.KREUZ))
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.value.equals(Statics.DAME)&& second.farbe.equals(Statics.KREUZ))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.HERZ)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.PIK))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.HERZ)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.HERZ: {
                switch (first.value){
                    case Statics.ZEHN:{
                        if((second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.value.equals(Statics.BUBE)&&(second.farbe.equals(Statics.PIK)||second.farbe.equals(Statics.KREUZ)))
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{
                        if((second.value.equals(Statics.DAME)&&(second.farbe.equals(Statics.PIK)||second.farbe.equals(Statics.KREUZ)))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ)
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.HERZ)))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case Statics.KARO: {
                switch (first.value){
                    case Statics.ZEHN:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.HERZ)
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.BUBE:{
                        if((second.value.equals(Statics.BUBE)&&!second.farbe.equals(Statics.KARO))
                                || second.value.equals(Statics.DAME)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.DAME:{

                        if((second.value.equals(Statics.DAME)&&!second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.KOENIG:{
                        if(second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.HERZ)
                                || (second.value.equals(Statics.ZEHN)&&second.farbe.equals(Statics.KARO))
                                || (second.value.equals(Statics.ASS)&&second.farbe.equals(Statics.KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case Statics.ASS: {
                        if (second.value.equals(Statics.BUBE)
                                || second.value.equals(Statics.DAME)
                                || second.farbe.equals(Statics.HERZ)) {
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
