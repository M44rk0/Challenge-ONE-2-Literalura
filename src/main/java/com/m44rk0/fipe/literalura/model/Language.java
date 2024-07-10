package com.m44rk0.fipe.literalura.model;

public class Language {

    public static String convert(String language) {
        return switch (language) {
            case "portugues" -> "pt";
            case "ingles" -> "en";
            case "espanhol" -> "es";
            case "frances" -> "fr";
            case "italiano" -> "it";
            case "alemao" -> "de";
            case "chines" -> "zh";
            case "japones" -> "ja";
            case "coreano" -> "ko";
            case "russo" -> "ru";
            case "arabe" -> "ar";
            case "hebraico" -> "he";
            case "grego" -> "el";
            case "turco" -> "tr";
            case "polones" -> "pl";
            case "sueco" -> "sv";
            case "noruegues" -> "no";
            case "dinamarques" -> "da";
            case "finlandes" -> "fi";
            case "holandes" -> "nl";
            case "islandes" -> "is";
            case "hindi" -> "hi";
            case "bengali" -> "bn";
            case "tamil" -> "ta";
            case "telugo" -> "te";
            case "marati" -> "mr";
            case "urdu" -> "ur";
            case "persa" -> "fa";
            case "indonesio" -> "id";
            case "malasio" -> "ms";
            case "filipino" -> "tl";
            case "vietnamita" -> "vi";
            case "tailandes" -> "th";
            case "malaio" -> "ms";
            case "swahili" -> "sw";
            case "afrikaans" -> "af";
            case "zulu" -> "zu";
            case "xhosa" -> "xh";
            case "suaíli" -> "sw";
            case "hauçá" -> "ha";
            case "yoruba" -> "yo";
            case "igbo" -> "ig";
            case "somalí" -> "so";
            default -> language;
        };

    }
}
