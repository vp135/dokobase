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
    
    public static final boolean normalGame (Card first, Card second, boolean schwein) {
        //System.out.println(first.toString() +":"+ second.toString()+ " (Schwein:"+schwein+")");
        switch (first.farbe) {
            case KREUZ: {
                switch (first.value){
                    case ZEHN:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KARO)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KREUZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case BUBE:{
                        if(second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO)&&schwein)){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO)&&schwein)){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KARO)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(KREUZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KARO)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
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
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&second.farbe.equals(KREUZ))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO)&&schwein)){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(DAME)&& second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO)&&schwein)){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KARO)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(PIK))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(PIK))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KARO)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
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
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&(second.farbe.equals(PIK)||second.farbe.equals(KREUZ)))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO)&&schwein)){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(DAME)&&(second.farbe.equals(PIK)||second.farbe.equals(KREUZ)))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO)&&schwein)){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.farbe.equals(KARO)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(HERZ)))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.farbe.equals(KARO)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ)))){
                            return false;
                        }else {
                            return true;
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
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&!second.farbe.equals(KARO))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO)&&schwein)){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(DAME)&&!second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO)&&schwein)){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(KARO))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ASS:{
                        if(schwein){
                            return true;
                        }
                        else{
                            if(second.value.equals(BUBE)
                                    || second.value.equals(DAME)
                                    || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                                return false;
                            }else {
                                return true;
                            }
                        }
                    }
                }
                break;
            }

        }
        log.error("Error");
        return false;
    }

    public static boolean damen(Card first, Card second) {
        //System.out.println(first.toString() +":"+ second.toString());
        switch (first.farbe) {
            case KREUZ: {
                switch (first.value){
                    case DAME: {
                        return true;
                    }
                    case ASS:{
                        if(second.value.equals(DAME)){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(KREUZ))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KREUZ))) {
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case BUBE:{
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KREUZ))
                                || (second.value.equals(KOENIG) && second.farbe.equals(KREUZ))) {
                            return false;
                        }else {
                            return true;
                        }
                    }
                }
                break;
            }
            case PIK: {
                switch (first.value){
                    case DAME: {
                        if(second.value.equals(DAME)&&second.farbe.equals(KREUZ)){
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(DAME)){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(PIK))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(PIK))
                                || (second.value.equals(ZEHN) && second.farbe.equals(PIK))) {
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case BUBE:{
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(PIK))
                                || (second.value.equals(ZEHN) && second.farbe.equals(PIK))
                                || (second.value.equals(KOENIG) && second.farbe.equals(PIK))) {
                            return false;
                        }else {
                            return true;
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
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(DAME)){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(HERZ))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))) {
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case BUBE:{
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))
                                || (second.value.equals(KOENIG) && second.farbe.equals(HERZ))) {
                            return false;
                        }else {
                            return true;
                        }
                    }
                }
                break;
            }
            case KARO: {
                switch (first.value){
                    case DAME: {
                        if((second.value.equals(DAME)&&!second.farbe.equals(KARO))){
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(DAME)){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(KARO))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KARO))) {
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case BUBE:{
                        if (second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KARO))
                                || (second.value.equals(KOENIG) && second.farbe.equals(KARO))) {
                            return false;
                        }else {
                            return true;
                        }
                    }
                }
                break;
            }

        }
        log.error("Error");
        return false;
    }

    public static boolean buben(Card first, Card second) {
        //System.out.println(first.toString() +":"+ second.toString());
        switch (first.farbe) {
            case KREUZ: {
                switch (first.value){
                    case BUBE: {
                        return true;
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(KREUZ))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KREUZ))) {
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case DAME:{
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KREUZ))
                                || (second.value.equals(KOENIG) && second.farbe.equals(KREUZ))) {
                            return false;
                        }else {
                            return true;
                        }
                    }
                }
                break;
            }
            case PIK: {
                switch (first.value){
                    case BUBE: {
                        if(second.value.equals(BUBE)&&second.farbe.equals(KREUZ)){
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(PIK))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(PIK))
                                || (second.value.equals(ZEHN) && second.farbe.equals(PIK))) {
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case DAME:{
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(PIK))
                                || (second.value.equals(ZEHN) && second.farbe.equals(PIK))
                                || (second.value.equals(KOENIG) && second.farbe.equals(PIK))) {
                            return false;
                        }else {
                            return true;
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
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(HERZ))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))) {
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case DAME:{
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))
                                || (second.value.equals(KOENIG) && second.farbe.equals(HERZ))) {
                            return false;
                        }else {
                            return true;
                        }
                    }
                }
                break;
            }
            case KARO: {
                switch (first.value){
                    case BUBE: {
                        if((second.value.equals(BUBE)&&!second.farbe.equals(KARO))){
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(KARO))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KARO))) {
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case DAME:{
                        if (second.value.equals(BUBE)
                                || (second.value.equals(ASS) && second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KARO))
                                || (second.value.equals(KOENIG) && second.farbe.equals(KARO))) {
                            return false;
                        }else {
                            return true;
                        }
                    }
                }
                break;
            }

        }
        log.error("Error");
        return false;
    }

    public static boolean bubendamen(Card first, Card second) {
        //System.out.println(first.toString() + ":" + second.toString());
        switch (first.farbe) {
            case KREUZ: {
                switch (first.value) {
                    case DAME: {
                        return true;
                    }
                    case BUBE: {
                        if (second.value.equals(DAME)) {
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                    case ASS: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(KREUZ))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case KOENIG: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KREUZ))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
                break;
            }
            case PIK: {
                switch (first.value) {
                    case DAME: {
                        if (second.value.equals(DAME) && second.farbe.equals(KREUZ)) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case BUBE: {
                        if (second.value.equals(DAME)
                                || (second.value.equals(BUBE) && second.farbe.equals(KREUZ))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case ASS: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(PIK))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case KOENIG: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(PIK))
                                || (second.value.equals(ZEHN) && second.farbe.equals(PIK))) {
                            return false;
                        } else {
                            return true;
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
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case BUBE: {
                        if (second.value.equals(DAME)
                                || (second.value.equals(BUBE) && second.farbe.equals(KREUZ))
                                || (second.value.equals(BUBE) && second.farbe.equals(PIK))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case ASS: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(HERZ))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case KOENIG: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
                break;
            }
            case KARO: {
                switch (first.value) {
                    case DAME: {
                        if (second.value.equals(DAME) && !second.farbe.equals(KARO)) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case BUBE: {
                        if (second.value.equals(DAME)
                                || (second.value.equals(BUBE) && !second.farbe.equals(KARO))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case ASS: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case ZEHN: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(KARO))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case KOENIG: {
                        if (second.value.equals(BUBE) || second.value.equals(DAME)
                                || (second.value.equals(ASS) && second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KARO))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
                break;
            }

        }
        log.error("Error");
        return false;
    }

    public static boolean fleischlos(Card first, Card second) {
        //System.out.println(first.toString() + ":" + second.toString());
        switch (first.farbe) {
            case KREUZ: {
                switch (first.value) {
                    case ASS: {
                        return true;
                    }
                    case ZEHN: {
                        if (second.value.equals(ASS) && second.farbe.equals(KREUZ)) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case KOENIG: {
                        if ((second.value.equals(ASS) && second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KREUZ))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case DAME: {
                        if ((second.value.equals(ASS) && second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KREUZ))
                                || (second.value.equals(KOENIG) && second.farbe.equals(KREUZ))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case BUBE: {
                        if ((second.value.equals(ASS) && second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KREUZ))
                                || (second.value.equals(KOENIG) && second.farbe.equals(KREUZ))
                                || (second.value.equals(DAME) && second.farbe.equals(KREUZ))) {
                            return false;
                        }
                        else {
                            return true;
                        }
                    }
                }
                break;
            }
            case PIK: {
                switch (first.value) {
                    case ASS: {
                        return true;
                    }
                    case ZEHN: {
                        if (second.value.equals(ASS) && second.farbe.equals(PIK)) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case KOENIG: {
                        if ((second.value.equals(ASS) && second.farbe.equals(PIK))
                                || (second.value.equals(ZEHN) && second.farbe.equals(PIK))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case DAME: {
                        if ((second.value.equals(ASS) && second.farbe.equals(PIK))
                                || (second.value.equals(ZEHN) && second.farbe.equals(PIK))
                                || (second.value.equals(KOENIG) && second.farbe.equals(PIK))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case BUBE: {
                        if ((second.value.equals(ASS) && second.farbe.equals(PIK))
                                || (second.value.equals(ZEHN) && second.farbe.equals(PIK))
                                || (second.value.equals(KOENIG) && second.farbe.equals(PIK))
                                || (second.value.equals(DAME) && second.farbe.equals(PIK))) {
                            return false;
                        }
                        else {
                            return true;
                        }
                    }
                }
                break;

            }
            case HERZ: {
                switch (first.value) {
                    case ASS: {
                        return true;
                    }
                    case ZEHN: {
                        if (second.value.equals(ASS) && second.farbe.equals(HERZ)) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case KOENIG: {
                        if ((second.value.equals(ASS) && second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case DAME: {
                        if ((second.value.equals(ASS) && second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))
                                || (second.value.equals(KOENIG) && second.farbe.equals(HERZ))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case BUBE: {
                        if ((second.value.equals(ASS) && second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))
                                || (second.value.equals(KOENIG) && second.farbe.equals(HERZ))
                                || (second.value.equals(DAME) && second.farbe.equals(HERZ))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
                break;
            }
            case KARO: {
                switch (first.value) {
                    case ASS: {
                        return true;
                    }
                    case ZEHN: {
                        if (second.value.equals(ASS) && second.farbe.equals(KARO)) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case KOENIG: {
                        if ((second.value.equals(ASS) && second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KARO))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case DAME: {
                        if ((second.value.equals(ASS) && second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KARO))
                                || (second.value.equals(KOENIG) && second.farbe.equals(KARO))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                    case BUBE: {
                        if ((second.value.equals(ASS) && second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN) && second.farbe.equals(KARO))
                                || (second.value.equals(KOENIG) && second.farbe.equals(KARO))
                                || (second.value.equals(DAME) && second.farbe.equals(KARO))) {
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                }
                break;
            }
        }
        log.error("Error");
        return false;
    }

    public static boolean kreuz(Card first, Card second) {
        //System.out.println(first.toString() +":"+ second.toString());
        switch (first.farbe) {
            case KREUZ: {
                switch (first.value){
                    case ZEHN:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KREUZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case BUBE:{
                        if(second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(KREUZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KREUZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
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
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&second.farbe.equals(KREUZ))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(DAME)&& second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KREUZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(PIK))
                                || (second.value.equals(ASS)&&second.farbe.equals(PIK))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KREUZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                }
                break;
            }
            case HERZ: {
                switch (first.value){
                    case ZEHN:{
                        if((second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&(second.farbe.equals(PIK)||second.farbe.equals(KREUZ)))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case DAME:{
                        if((second.value.equals(DAME)&&(second.farbe.equals(PIK)||second.farbe.equals(KREUZ)))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KREUZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KREUZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
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
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&!second.farbe.equals(KARO))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(DAME)&&!second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KREUZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(KARO))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ASS: {
                        if (second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(KREUZ)
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
                break;
            }

        }
        log.error("Error");
        return false;
    }

    public static boolean pik(Card first, Card second) {
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
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case BUBE:{
                        if(second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(PIK)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(KREUZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KREUZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(PIK)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
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
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&second.farbe.equals(KREUZ))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(DAME)&& second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(PIK))
                                || (second.value.equals(ASS)&&second.farbe.equals(PIK))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                }
                break;
            }
            case HERZ: {
                switch (first.value){
                    case ZEHN:{
                        if((second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&(second.farbe.equals(PIK)||second.farbe.equals(KREUZ)))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case DAME:{
                        if((second.value.equals(DAME)&&(second.farbe.equals(PIK)||second.farbe.equals(KREUZ)))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(PIK)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(PIK)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
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
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&!second.farbe.equals(KARO))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(DAME)&&!second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(PIK)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(KARO))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ASS: {
                        if (second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(PIK)
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
                break;
            }

        }
        log.error("Error");
        return false;
    }

    public static boolean herz(Card first, Card second) {
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
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case BUBE:{
                        if(second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(HERZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(KREUZ))
                                || (second.value.equals(ASS)&&second.farbe.equals(KREUZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(HERZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
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
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&second.farbe.equals(KREUZ))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(DAME)&& second.farbe.equals(KREUZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(HERZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(PIK))
                                || (second.value.equals(ASS)&&second.farbe.equals(PIK))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(HERZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                }
                break;
            }
            case HERZ: {
                switch (first.value){
                    case ZEHN:{
                        if((second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&(second.farbe.equals(PIK)||second.farbe.equals(KREUZ)))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case DAME:{
                        if((second.value.equals(DAME)&&(second.farbe.equals(PIK)||second.farbe.equals(KREUZ)))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ)
                                || (second.value.equals(ASS)&&second.farbe.equals(HERZ)))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ASS:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
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
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case BUBE:{
                        if((second.value.equals(BUBE)&&!second.farbe.equals(KARO))
                                || second.value.equals(DAME)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case DAME:{

                        if((second.value.equals(DAME)&&!second.farbe.equals(KARO))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case KOENIG:{
                        if(second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(HERZ)
                                || (second.value.equals(ZEHN)&&second.farbe.equals(HERZ))
                                || (second.value.equals(ZEHN)&&second.farbe.equals(KARO))
                                || (second.value.equals(ASS)&&second.farbe.equals(KARO))){
                            return false;
                        }else {
                            return true;
                        }
                    }
                    case ASS: {
                        if (second.value.equals(BUBE)
                                || second.value.equals(DAME)
                                || second.farbe.equals(HERZ)
                                || (second.value.equals(ZEHN) && second.farbe.equals(HERZ))) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
                break;
            }

        }
        log.error("Error");
        return false;
    }

    public static boolean karo(Card first, Card second, boolean schwein) {
        return normalGame(first,second,schwein);
    }
}
