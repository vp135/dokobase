package base;

public class Compare {

    private static final String KREUZ = "Kreuz";
    private static final String PIK = "Pik";
    private static final String HERZ = "Herz";
    private static final String KARO = "Karo";

    private static final String ZEHN = "10";
    private static final String BUBE = "Bube";
    private static final String DAME = "Dame";
    private static final String KOENIG = "Koenig";
    private static final String ASS = "Ass";

    private final static Logger log = new Logger(Compare.class.getName());
    
    public static final Card normalGame (Card first, Card second, boolean schwein) {
        switch (first.farbe) {
            case KREUZ: {
                switch (first.value){
                    case ZEHN:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KARO)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case BUBE:{
                        if(second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KARO)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KARO)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case PIK: {
                switch (first.value){
                    case ZEHN:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KARO)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&second.farbe.equals(KREUZ))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(DAME)&& second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KARO)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(PIK))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KARO)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case HERZ: {
                switch (first.value){
                    case ZEHN:{
                        if((second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&(second.farbe.equals(PIK)||second.farbe.equals(KREUZ)))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(DAME)&&(second.farbe.equals(PIK)||second.farbe.equals(KREUZ)))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.farbe.equals(KARO)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(HERZ)))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.farbe.equals(KARO)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ)))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case KARO: {
                switch (first.value){
                    case ZEHN:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&!second.farbe.equals(KARO))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(DAME)&&!second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO)&&schwein)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(KARO))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ASS:{
                        if(schwein){
                            return first;
                        }
                        else{
                            if(second.value.equals(BUBE)
                                    || second.value.equals(DAME)
                                    || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
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
            case KREUZ: {
                switch (first.value){
                    case DAME: {
                        return first;
                    }
                    case ASS:{
                        if(second.value.equals(DAME)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KREUZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case BUBE:{
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KREUZ))
                                || (second.value.equals(KOENIG) && second.farbe.equals(KREUZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case PIK: {
                switch (first.value){
                    case DAME: {
                        if(second.value.equals(DAME)&&second.farbe.equals(KREUZ)){
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(DAME)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(PIK))
                                || (second.value.equals(ZEHN) && second.farbe.equals(PIK))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case BUBE:{
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(PIK))
                                || (second.value.equals(ZEHN) && second.farbe.equals(PIK))
                                || (second.value.equals(KOENIG) && second.farbe.equals(PIK))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case HERZ: {
                switch (first.value){
                    case DAME: {
                        if((second.value.equals(DAME)&&second.farbe.equals(KREUZ))
                        ||(second.value.equals(DAME)&&second.farbe.equals(PIK))){
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(DAME)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case BUBE:{
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))
                                || (second.value.equals(KOENIG) && second.farbe.equals(HERZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case KARO: {
                switch (first.value){
                    case DAME: {
                        if((second.value.equals(DAME)&&!second.farbe.equals(KARO))){
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(DAME)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(KARO))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KARO))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case BUBE:{
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KARO))
                                || (second.value.equals(KOENIG) && second.farbe.equals(KARO))) {
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
            case KREUZ: {
                switch (first.value){
                    case BUBE: {
                        return first;
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KREUZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case DAME:{
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KREUZ))
                                || (second.value.equals(KOENIG) && second.farbe.equals(KREUZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case PIK: {
                switch (first.value){
                    case BUBE: {
                        if(second.value.equals(BUBE)&&second.farbe.equals(KREUZ)){
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(PIK))
                                || (second.value.equals(ZEHN) && second.farbe.equals(PIK))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case DAME:{
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(PIK))
                                || (second.value.equals(ZEHN) && second.farbe.equals(PIK))
                                || (second.value.equals(KOENIG) && second.farbe.equals(PIK))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case HERZ: {
                switch (first.value){
                    case BUBE: {
                        if((second.value.equals(BUBE)&&second.farbe.equals(KREUZ))
                                ||(second.value.equals(BUBE)&&second.farbe.equals(PIK))){
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case DAME:{
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))
                                || (second.value.equals(KOENIG) && second.farbe.equals(HERZ))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case KARO: {
                switch (first.value){
                    case BUBE: {
                        if((second.value.equals(BUBE)&&!second.farbe.equals(KARO))){
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(KARO))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KARO))) {
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case DAME:{
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KARO))
                                || (second.value.equals(KOENIG) && second.farbe.equals(KARO))) {
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
            case KREUZ: {
                switch (first.value) {
                    case DAME: {
                        return first;
                    }
                    case BUBE: {
                        if (second.value.equals(DAME)) {
                            return second;
                        }
                        else{
                            return first;
                        }
                    }
                    case ASS: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case KOENIG: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                }
                break;
            }
            case PIK: {
                switch (first.value) {
                    case DAME: {
                        if (second.value.equals(DAME) && second.farbe.equals(KREUZ)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case BUBE: {
                        if (second.value.equals(DAME)
                                || (second.value.equals(BUBE) && second.farbe.equals(KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case ASS: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case KOENIG: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(PIK))
                                || (second.value.equals(ZEHN) && second.farbe.equals(PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                }
                break;
            }
            case HERZ: {
                switch (first.value) {
                    case DAME: {
                        if ((second.value.equals(DAME) && second.farbe.equals(KREUZ))
                                || (second.value.equals(DAME) && second.farbe.equals(PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case BUBE: {
                        if (second.value.equals(DAME)
                                || (second.value.equals(BUBE) && second.farbe.equals(KREUZ))
                                || (second.value.equals(BUBE) && second.farbe.equals(PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case ASS: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case KOENIG: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                }
                break;
            }
            case KARO: {
                switch (first.value) {
                    case DAME: {
                        if (second.value.equals(DAME) && !second.farbe.equals(KARO)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case BUBE: {
                        if (second.value.equals(DAME)
                                || (second.value.equals(BUBE) && !second.farbe.equals(KARO))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case ASS: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(KARO))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case KOENIG: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KARO))) {
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
            case KREUZ: {
                switch (first.value) {
                    case ASS: {
                        return first;
                    }
                    case ZEHN: {
                        if (second.value.equals(ASS) && second.farbe.equals(KREUZ)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case KOENIG: {
                        if ((second.value.equals(ASS) && second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case DAME: {
                        if ((second.value.equals(ASS) && second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KREUZ))
                                || (second.value.equals(KOENIG) && second.farbe.equals(KREUZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case BUBE: {
                        if ((second.value.equals(ASS) && second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KREUZ))
                                || (second.value.equals(KOENIG) && second.farbe.equals(KREUZ))
                                || (second.value.equals(DAME) && second.farbe.equals(KREUZ))) {
                            return second;
                        }
                        else {
                            return first;
                        }
                    }
                }
                break;
            }
            case PIK: {
                switch (first.value) {
                    case ASS: {
                        return first;
                    }
                    case ZEHN: {
                        if (second.value.equals(ASS) && second.farbe.equals(PIK)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case KOENIG: {
                        if ((second.value.equals(ASS) && second.farbe.equals(PIK))
                                || (second.value.equals(ZEHN) && second.farbe.equals(PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case DAME: {
                        if ((second.value.equals(ASS) && second.farbe.equals(PIK))
                                || (second.value.equals(ZEHN) && second.farbe.equals(PIK))
                                || (second.value.equals(KOENIG) && second.farbe.equals(PIK))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case BUBE: {
                        if ((second.value.equals(ASS) && second.farbe.equals(PIK))
                                || (second.value.equals(ZEHN) && second.farbe.equals(PIK))
                                || (second.value.equals(KOENIG) && second.farbe.equals(PIK))
                                || (second.value.equals(DAME) && second.farbe.equals(PIK))) {
                            return second;
                        }
                        else {
                            return first;
                        }
                    }
                }
                break;

            }
            case HERZ: {
                switch (first.value) {
                    case ASS: {
                        return first;
                    }
                    case ZEHN: {
                        if (second.value.equals(ASS) && second.farbe.equals(HERZ)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case KOENIG: {
                        if ((second.value.equals(ASS) && second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case DAME: {
                        if ((second.value.equals(ASS) && second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))
                                || (second.value.equals(KOENIG) && second.farbe.equals(HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case BUBE: {
                        if ((second.value.equals(ASS) && second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))
                                || (second.value.equals(KOENIG) && second.farbe.equals(HERZ))
                                || (second.value.equals(DAME) && second.farbe.equals(HERZ))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                }
                break;
            }
            case KARO: {
                switch (first.value) {
                    case ASS: {
                        return first;
                    }
                    case ZEHN: {
                        if (second.value.equals(ASS) && second.farbe.equals(KARO)) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case KOENIG: {
                        if ((second.value.equals(ASS) && second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KARO))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case DAME: {
                        if ((second.value.equals(ASS) && second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KARO))
                                || (second.value.equals(KOENIG) && second.farbe.equals(KARO))) {
                            return second;
                        } else {
                            return first;
                        }
                    }
                    case BUBE: {
                        if ((second.value.equals(ASS) && second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KARO))
                                || (second.value.equals(KOENIG) && second.farbe.equals(KARO))
                                || (second.value.equals(DAME) && second.farbe.equals(KARO))) {
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
            case KREUZ: {
                switch (first.value){
                    case ZEHN:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case BUBE:{
                        if(second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(KREUZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case PIK: {
                switch (first.value){
                    case ZEHN:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KREUZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&second.farbe.equals(KREUZ))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(DAME)&& second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KREUZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(PIK))
                                || (second.value.equals(ASS)&&second.farbe.equals(PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KREUZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case HERZ: {
                switch (first.value){
                    case ZEHN:{
                        if((second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&(second.farbe.equals(PIK)||second.farbe.equals(KREUZ)))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case DAME:{
                        if((second.value.equals(DAME)&&(second.farbe.equals(PIK)||second.farbe.equals(KREUZ)))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KREUZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KREUZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case KARO: {
                switch (first.value){
                    case ZEHN:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KREUZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&!second.farbe.equals(KARO))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(DAME)&&!second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KREUZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(KARO))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ASS: {
                        if (second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KREUZ)
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))) {
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
            case KREUZ: {
                switch (first.value){
                    case ZEHN:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(PIK)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case BUBE:{
                        if(second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(PIK)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(KREUZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(PIK)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case PIK: {
                switch (first.value){
                    case ZEHN:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&second.farbe.equals(KREUZ))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(DAME)&& second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(PIK))
                                || (second.value.equals(ASS)&&second.farbe.equals(PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case HERZ: {
                switch (first.value){
                    case ZEHN:{
                        if((second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&(second.farbe.equals(PIK)||second.farbe.equals(KREUZ)))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case DAME:{
                        if((second.value.equals(DAME)&&(second.farbe.equals(PIK)||second.farbe.equals(KREUZ)))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(PIK)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(PIK)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case KARO: {
                switch (first.value){
                    case ZEHN:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(PIK)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&!second.farbe.equals(KARO))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(DAME)&&!second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(PIK)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(KARO))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ASS: {
                        if (second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(PIK)
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))) {
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
            case KREUZ: {
                switch (first.value){
                    case ZEHN:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(HERZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case BUBE:{
                        if(second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case DAME:{
                        if((second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(HERZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(KREUZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KREUZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(HERZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case PIK: {
                switch (first.value){
                    case ZEHN:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(HERZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&second.farbe.equals(KREUZ))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(DAME)&& second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(HERZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(PIK))
                                || (second.value.equals(ASS)&&second.farbe.equals(PIK))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(HERZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case HERZ: {
                switch (first.value){
                    case ZEHN:{
                        if((second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&(second.farbe.equals(PIK)||second.farbe.equals(KREUZ)))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case DAME:{
                        if((second.value.equals(DAME)&&(second.farbe.equals(PIK)||second.farbe.equals(KREUZ)))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ)
                                || (second.value.equals(ASS)&&second.farbe.equals(HERZ)))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                }
                break;
            }
            case KARO: {
                switch (first.value){
                    case ZEHN:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(HERZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&!second.farbe.equals(KARO))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(DAME)&&!second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(HERZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(KARO))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO))){
                            return second;
                        }else {
                            return first;
                        }
                    }
                    case ASS: {
                        if (second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(HERZ)
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))) {
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
