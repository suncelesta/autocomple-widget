package com.autocomple.data;

import com.autocomple.common.StringUtils;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class FakeSuggestionsProvider implements SuggestionsAsyncProvider {

    private final Set<String> db = new HashSet<>();

    public FakeSuggestionsProvider() {
        String[] suggestions = ss.split("\n");
        for (String suggestion : suggestions) {
            db.add(suggestion.trim());
        }
    }

    public static void main(String[] args) {
        for (String s : new FakeSuggestionsProvider().db) {
            System.out.println(s);
        }

    }

    @Override
    public void getSuggestions(String query, final AsyncCallback<JsArray<SuggestionJso>> callback) {
        final JsArray<SuggestionJso> result = (JsArray<SuggestionJso>) JsArray.createArray();

        if (StringUtils.isEmpty(query)) {
            callback.onSuccess(result);
            return;
        }

        final String nGram = query.toLowerCase();
        final int[] count = {0};
        final Iterator<String> it = db.iterator();

        Scheduler.get().scheduleIncremental(new Scheduler.RepeatingCommand() {
            @Override
            public boolean execute() {
                if (it.hasNext()) {
                    String s = it.next();

                    if (s.startsWith(nGram)) {
                        StringBuilder b = new StringBuilder();

                        b.append(nGram)
                                .append("<b>")
                                .append(s.replaceFirst(nGram, StringUtils.EMPTY))
                                .append("</b>");

                        result.push(SuggestionJso.create(b.toString()));

                        if (++count[0] >= 4) {
                            callback.onSuccess(result);
                            return false;
                        }
                    }
                }

                boolean hasNext = it.hasNext();
                if (!hasNext) {
                    callback.onSuccess(result);
                }

                return hasNext;
            }
        });
    }


    private String ss = "hello kitty\n" +
            "hello\n" +
            "hello ladies\n" +
            "hello kitty games\n" +
            "hellofax\n" +
            "hellogiggles\n" +
            "hello fresh\n" +
            "hello beautiful\n" +
            "hello in japanese\n" +
            "hello dolly\n" +
            "hello kitty\n" +
            "hello kitty games\n" +
            "hello dolly\n" +
            "hello magazine\n" +
            "hello ladies\n" +
            "hello beautiful\n" +
            "hello in japanese\n" +
            "hello apparel\n" +
            "hello kitty coloring pages\n" +
            "hellofax\n" +
            "hello apparel\n" +
            "hello again\n" +
            "hello again lyrics\n" +
            "hello again friend of a friend\n" +
            "hello all\n" +
            "hello again movie\n" +
            "hello arabic\n" +
            "hello android\n" +
            "hello alone lyrics\n" +
            "hello and company\n" +
            " hello beautiful\n" +
            " hello baby\n" +
            " hello book of mormon\n" +
            " hello baby season 8\n" +
            " hellobar\n" +
            " hello beyonce lyrics\n" +
            " hello beautiful in spanish\n" +
            " hello bee\n" +
            " hello book of mormon lyrics\n" +
            " hello brother\n" +
            " hello cosplay\n" +
            " hello cupcake austin\n" +
            " hello caller\n" +
            " hello cuteness\n" +
            " hello clarice\n" +
            " hello cupcake\n" +
            " hello cupid\n" +
            " hello country bumpkin\n" +
            " hello counselor\n" +
            " hello chinese\n" +
            " hello dolly\n" +
            " hello darkness my old friend\n" +
            " hello direct\n" +
            " hello dolly akron\n" +
            " hello dolly lyrics\n" +
            " hello dolly movie\n" +
            " hello darlin\n" +
            " hello dolly ej thomas hall\n" +
            " hello dolly tour\n" +
            " hello down there\n" +
            " hello evanescence\n" +
            " hello echo\n" +
            " hello enso\n" +
            " hello everybody\n" +
            " hello envoy\n" +
            " hello everyone\n" +
            " hello eminem lyrics\n" +
            " hello eminem\n" +
            " hello erin\n" +
            " hello especially lyrics\n" +
            " hellofax\n" +
            " hello fresh\n" +
            " hello flo\n" +
            " hello florida\n" +
            " hello fashion blog\n" +
            " hello flight\n" +
            " hello flawless\n" +
            " hello fresh reviews\n" +
            " hello fresh coupon\n" +
            " hello fear lyrics\n" +
            " hello gorgeous\n" +
            " hellogiggles\n" +
            " hello goodbye lyrics\n" +
            " hello games\n" +
            " hello goodbye\n" +
            " hello gorgeous hair extensions\n" +
            " hello good morning\n" +
            " hello good morning lyrics\n" +
            " hello goodbye beatles\n" +
            " hello google\n" +
            " hello hello\n" +
            " hello herman\n" +
            " hello happiness blog\n" +
            " hello how are you\n" +
            " hello hero\n" +
            " hello how are you lyrics\n" +
            " hello health\n" +
            " hello harvey prince\n" +
            " hello hello lyrics\n" +
            " hello haters damn y'all mad\n" +
            " hello in japanese\n" +
            " hello in russian\n" +
            " hello in german\n" +
            " hello in italian\n" +
            " hello in different languages\n" +
            " hello in french\n" +
            " hello in vietnamese\n" +
            " hello in spanish\n" +
            " hello in arabic\n" +
            " hello is it me you're looking for\n" +
            " hello jaxon blog\n" +
            " hello japanese\n" +
            " hello josephine\n" +
            " hello january\n" +
            " hello jaxon gomi\n" +
            " hello jaden smith\n" +
            " hello jello\n" +
            " hello kitty\n" +
            " hello jadoo\n" +
            " hellojetblue\n" +
            " hello kitty\n" +
            " hello kitty games\n" +
            " hello kitty store\n" +
            " hello kitty coloring pages\n" +
            " hello kitty pictures\n" +
            " hello kitty vans\n" +
            " hello kitty purse\n" +
            " hello kitty characters\n" +
            " hello kitty videos\n" +
            " hello kitty slippers\n" +
            " hello ladies\n" +
            " hello lyrics\n" +
            " hello ladies season 2\n" +
            " hello little one ocala fl\n" +
            " hello larry\n" +
            " hello lucky\n" +
            " hello ladies imdb\n" +
            " hello literacy\n" +
            " hello ladies cancelled\n" +
            " hello lyrics karmin\n" +
            " hello magazine\n" +
            " hello my name is\n" +
            " hello my name is lyrics\n" +
            " hello music\n" +
            " hello miss apple\n" +
            " hello merch\n" +
            " hello my baby\n" +
            " hello my name is chords\n" +
            " hello my old heart\n" +
            " hello mary lou\n" +
            " hello nurse\n" +
            " hello neighbor\n" +
            " hello novine\n" +
            " hello newman\n" +
            " hello nasty\n" +
            " hello nurse animaniacs\n" +
            " hello ninja\n" +
            " hello nu'est lyrics\n" +
            " hello newlywed life\n" +
            " hello nomad\n" +
            " hello online\n" +
            " hello operator\n" +
            " hello october\n" +
            " hello operator lyrics\n" +
            " hello old friend\n" +
            " hello operator tab\n" +
            " hello oral care\n" +
            " hello origin\n" +
            " hell on wheels\n" +
            " hello ocean\n" +
            " hello poetry\n" +
            " hello products\n" +
            " hello parry\n" +
            " hello project\n" +
            " hello panda\n" +
            " hello perfume\n" +
            " hello portuguese\n" +
            " hello panda cookies\n" +
            " hello pizza\n" +
            " hello poppet\n" +
            " helloquizzy\n" +
            " hello quotes\n" +
            " hello q\n" +
            " helloquizzy harry potter\n" +
            " hello quotes tumblr\n" +
            " helloquizzy sorting hat\n" +
            " hello quo\n" +
            " hello quad cities\n" +
            " hello quotes pinterest\n" +
            " hello quotes from movies\n" +
            " hello ross\n" +
            " hello run\n" +
            " hello ruby\n" +
            " hello russian\n" +
            " hello rockview\n" +
            " hello ross tickets\n" +
            " hello receipts\n" +
            " hello royalty\n" +
            " hello richie\n" +
            " hello ross show\n" +
            " hello sign\n" +
            " hello song\n" +
            " hello sister goodbye life\n" +
            " hello scheduling\n" +
            " hello sms\n" +
            " hello seattle lyrics\n" +
            " hellospy\n" +
            " hello sleepwalkers\n" +
            " hello santa\n" +
            " hello stranger\n" +
            " hello this is dog\n" +
            " hello toothpaste\n" +
            " hello there\n" +
            " hello to all the children of the world\n" +
            " hello talk\n" +
            " hello the book of mormon\n" +
            " hello totem\n" +
            " hello there the angel from my nightmare\n" +
            " hello toothpaste review\n" +
            " hello thesaurus\n" +
            " hello uk\n" +
            " hello usa\n" +
            " hello u\n" +
            " hello unlimited\n" +
            " hello urban dictionary\n" +
            " hello usa book\n" +
            " hello uganda\n" +
            " hello urdu\n" +
            " hello um\n" +
            " hello universe\n" +
            " hello venus\n" +
            " hello vietnamese\n" +
            " hello vietnam\n" +
            " hello vino\n" +
            " hello venus profile\n" +
            " hello video\n" +
            " hello vagina lyrics\n" +
            " hello vietnam song\n" +
            " hello venus venus lyrics\n" +
            " hello vietnam lyrics\n" +
            " hello world\n" +
            " hello world communications\n" +
            " hello world java\n" +
            " hello world c++\n" +
            " hello world lyrics\n" +
            " hellowallet\n" +
            " hello walls\n" +
            " hello world python\n" +
            " hello world kid ink\n" +
            " hello washington condo\n" +
            " hello xkore\n" +
            " hello x i'm dad\n" +
            " hello xin chao\n" +
            " hello xoxo\n" +
            " hello x factor\n" +
            " hello x\n" +
            " hello xhosa\n" +
            " hello xiao jie korean drama\n" +
            " hello yes this is dog\n" +
            " hello yellowfin\n" +
            " hello young lovers\n" +
            " hello yes this is dog gif\n" +
            " hello young lovers lyrics\n" +
            " hello yarn\n" +
            " hello youtube\n" +
            " hello yellow crafts\n" +
            " hello yes this is balls\n" +
            " hello you fool i love you\n" +
            " hello zepp\n" +
            " hello zuko here\n" +
            " hello zepp mp3\n" +
            " hello zepp sheet music\n" +
            " hello zso\n" +
            " hello zep\n" +
            " hello zepp piano\n" +
            " hello zombie\n" +
            " hello zepp lyrics\n" +
            " hello zion\n";
}
