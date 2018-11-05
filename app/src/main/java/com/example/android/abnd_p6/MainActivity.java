package com.example.android.abnd_p6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String jsonTest = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":14012,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":1402,\"orderBy\":\"newest\",\"results\":[{\"id\":\"politics/2018/nov/05/let-the-people-judge-whether-a-brexit-deal-is-fit-for-purpose\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-11-05T00:01:42Z\",\"webTitle\":\"Let the people judge whether a Brexit deal is fit for purpose | Letters\",\"webUrl\":\"https://www.theguardian.com/politics/2018/nov/05/let-the-people-judge-whether-a-brexit-deal-is-fit-for-purpose\",\"apiUrl\":\"https://content.guardianapis.com/politics/2018/nov/05/let-the-people-judge-whether-a-brexit-deal-is-fit-for-purpose\",\"tags\":[],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"uk-news/2018/nov/04/keys-to-prosperity-in-the-norths-great-cities\",\"type\":\"article\",\"sectionId\":\"uk-news\",\"sectionName\":\"UK news\",\"webPublicationDate\":\"2018-11-04T18:36:23Z\",\"webTitle\":\"Keys to prosperity in the north’s great cities | Letters\",\"webUrl\":\"https://www.theguardian.com/uk-news/2018/nov/04/keys-to-prosperity-in-the-norths-great-cities\",\"apiUrl\":\"https://content.guardianapis.com/uk-news/2018/nov/04/keys-to-prosperity-in-the-norths-great-cities\",\"tags\":[],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"politics/2018/nov/04/no-deal-fears-over-drug-supplies\",\"type\":\"article\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-11-04T18:33:35Z\",\"webTitle\":\"No-deal fears over drug supplies | Letters\",\"webUrl\":\"https://www.theguardian.com/politics/2018/nov/04/no-deal-fears-over-drug-supplies\",\"apiUrl\":\"https://content.guardianapis.com/politics/2018/nov/04/no-deal-fears-over-drug-supplies\",\"tags\":[],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"commentisfree/2018/nov/04/the-labour-stagecoach-has-hit-a-bump-and-got-stuck-in-a-rut\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2018-11-04T08:00:25Z\",\"webTitle\":\"The Labour stagecoach has hit a bump and got stuck in a rut | Andrew Rawnsley\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2018/nov/04/the-labour-stagecoach-has-hit-a-bump-and-got-stuck-in-a-rut\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2018/nov/04/the-labour-stagecoach-has-hit-a-bump-and-got-stuck-in-a-rut\",\"tags\":[{\"id\":\"profile/andrewrawnsley\",\"type\":\"contributor\",\"webTitle\":\"Andrew Rawnsley\",\"webUrl\":\"https://www.theguardian.com/profile/andrewrawnsley\",\"apiUrl\":\"https://content.guardianapis.com/profile/andrewrawnsley\",\"references\":[],\"bio\":\"<p>Andrew Rawnsley is the Observer's award-winning chief political commentator. He is also a critically acclaimed broadcaster and author</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/contributor/2015/5/29/1432894730331/Andrew-Rawnsley.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2017/10/06/Andrew-Rawnsley,-L.png\",\"firstName\":\"rawnsley\",\"lastName\":\"\"}],\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"},{\"id\":\"business/2018/nov/04/unlike-david-cameron-we-cant-flee-brexit-disaster\",\"type\":\"article\",\"sectionId\":\"business\",\"sectionName\":\"Business\",\"webPublicationDate\":\"2018-11-04T07:00:21Z\",\"webTitle\":\"Unlike David Cameron, we can’t flee Brexit disaster | William Keegan\",\"webUrl\":\"https://www.theguardian.com/business/2018/nov/04/unlike-david-cameron-we-cant-flee-brexit-disaster\",\"apiUrl\":\"https://content.guardianapis.com/business/2018/nov/04/unlike-david-cameron-we-cant-flee-brexit-disaster\",\"tags\":[{\"id\":\"profile/williamkeegan\",\"type\":\"contributor\",\"webTitle\":\"William Keegan\",\"webUrl\":\"https://www.theguardian.com/profile/williamkeegan\",\"apiUrl\":\"https://content.guardianapis.com/profile/williamkeegan\",\"references\":[],\"bio\":\"<p>William Keegan is the Observer's senior economics commentator</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/contributor/2014/5/22/1400773487854/William-Keegan.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2017/10/09/William-Keegan,-L.png\",\"firstName\":\"keegan\",\"lastName\":\"\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"commentisfree/2018/nov/04/observer-view-on-brexit-campaign-funding-election-cheats\",\"type\":\"article\",\"sectionId\":\"commentisfree\",\"sectionName\":\"Opinion\",\"webPublicationDate\":\"2018-11-04T05:59:20Z\",\"webTitle\":\"The Observer view on campaign funding and election cheats | Observer editorial\",\"webUrl\":\"https://www.theguardian.com/commentisfree/2018/nov/04/observer-view-on-brexit-campaign-funding-election-cheats\",\"apiUrl\":\"https://content.guardianapis.com/commentisfree/2018/nov/04/observer-view-on-brexit-campaign-funding-election-cheats\",\"tags\":[{\"id\":\"profile/observer-editorials\",\"type\":\"contributor\",\"webTitle\":\"Observer editorial\",\"webUrl\":\"https://www.theguardian.com/profile/observer-editorials\",\"apiUrl\":\"https://content.guardianapis.com/profile/observer-editorials\",\"references\":[],\"bio\":\"<p>Editorials from <a href=\\\"http://observer.guardian.co.uk/\\\">the Observer</a></p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/pictures/2013/6/8/1370728049903/observerlogo_140x140.jpg\",\"firstName\":\"editorial\",\"lastName\":\"observer\"}],\"isHosted\":false,\"pillarId\":\"pillar/opinion\",\"pillarName\":\"Opinion\"},{\"id\":\"culture/2018/nov/02/feeling-blue-over-lack-of-plaques-for-women\",\"type\":\"article\",\"sectionId\":\"culture\",\"sectionName\":\"Culture\",\"webPublicationDate\":\"2018-11-02T16:51:39Z\",\"webTitle\":\"Feeling blue over lack of plaques for women | Letters\",\"webUrl\":\"https://www.theguardian.com/culture/2018/nov/02/feeling-blue-over-lack-of-plaques-for-women\",\"apiUrl\":\"https://content.guardianapis.com/culture/2018/nov/02/feeling-blue-over-lack-of-plaques-for-women\",\"tags\":[],\"isHosted\":false,\"pillarId\":\"pillar/arts\",\"pillarName\":\"Arts\"},{\"id\":\"society/2018/nov/02/delay-in-fobt-stake-cut-puts-lives-at-risk\",\"type\":\"article\",\"sectionId\":\"society\",\"sectionName\":\"Society\",\"webPublicationDate\":\"2018-11-02T16:50:39Z\",\"webTitle\":\"Delay in FOBT stake cut puts lives at risk | Letters\",\"webUrl\":\"https://www.theguardian.com/society/2018/nov/02/delay-in-fobt-stake-cut-puts-lives-at-risk\",\"apiUrl\":\"https://content.guardianapis.com/society/2018/nov/02/delay-in-fobt-stake-cut-puts-lives-at-risk\",\"tags\":[],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"},{\"id\":\"food/2018/nov/02/get-ready-for-greggs-nori-seaweed-bake\",\"type\":\"article\",\"sectionId\":\"food\",\"sectionName\":\"Food\",\"webPublicationDate\":\"2018-11-02T16:43:52Z\",\"webTitle\":\"Get ready for Greggs’ nori seaweed bake | Brief letters\",\"webUrl\":\"https://www.theguardian.com/food/2018/nov/02/get-ready-for-greggs-nori-seaweed-bake\",\"apiUrl\":\"https://content.guardianapis.com/food/2018/nov/02/get-ready-for-greggs-nori-seaweed-bake\",\"tags\":[],\"isHosted\":false,\"pillarId\":\"pillar/lifestyle\",\"pillarName\":\"Lifestyle\"},{\"id\":\"politics/blog/live/2018/nov/01/brexit-javid-overrules-nokes-and-rules-out-immediate-new-hiring-checks-for-firms-if-theres-no-deal-politics-live\",\"type\":\"liveblog\",\"sectionId\":\"politics\",\"sectionName\":\"Politics\",\"webPublicationDate\":\"2018-11-01T22:03:10Z\",\"webTitle\":\"Tracey Crouch resigns as sports minister over fixed odds betting terminals delay - as it happened\",\"webUrl\":\"https://www.theguardian.com/politics/blog/live/2018/nov/01/brexit-javid-overrules-nokes-and-rules-out-immediate-new-hiring-checks-for-firms-if-theres-no-deal-politics-live\",\"apiUrl\":\"https://content.guardianapis.com/politics/blog/live/2018/nov/01/brexit-javid-overrules-nokes-and-rules-out-immediate-new-hiring-checks-for-firms-if-theres-no-deal-politics-live\",\"tags\":[{\"id\":\"profile/andrewsparrow\",\"type\":\"contributor\",\"webTitle\":\"Andrew Sparrow\",\"webUrl\":\"https://www.theguardian.com/profile/andrewsparrow\",\"apiUrl\":\"https://content.guardianapis.com/profile/andrewsparrow\",\"references\":[],\"bio\":\"<p>Andrew Sparrow is a political correspondent at the Guardian. He writes the Guardian's daily live blog, <a href=\\\"http://www.theguardian.com/politics/series/politics-live-with-andrew-sparrow\\\">Politics live with Andrew Sparrow</a>.</p>\",\"bylineImageUrl\":\"https://uploads.guim.co.uk/2017/10/06/Andrew-Sparrow.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2017/10/06/Andrew_Sparrow,_R.png\",\"firstName\":\"sparrow\",\"lastName\":\"andrew\",\"twitterHandle\":\"AndrewSparrow\"},{\"id\":\"profile/kevin-rawlinson\",\"type\":\"contributor\",\"webTitle\":\"Kevin Rawlinson\",\"webUrl\":\"https://www.theguardian.com/profile/kevin-rawlinson\",\"apiUrl\":\"https://content.guardianapis.com/profile/kevin-rawlinson\",\"references\":[],\"bio\":\"<p>Kevin Rawlinson is a reporter at the Guardian. Twitter <a href=\\\"https://twitter.com/kevinjrawlinson?lang=en\\\">@KevinJRawlinson</a></p>\",\"firstName\":\"Kevin\",\"lastName\":\"Rawlinson\"},{\"id\":\"profile/severincarrell\",\"type\":\"contributor\",\"webTitle\":\"Severin Carrell\",\"webUrl\":\"https://www.theguardian.com/profile/severincarrell\",\"apiUrl\":\"https://content.guardianapis.com/profile/severincarrell\",\"references\":[],\"bio\":\"<p>Severin Carrell is <a href=\\\"http://www.guardian.co.uk/politics/scotland\\\">Scotland</a> editor for the <a href=\\\"http://www.guardian.co.uk/theguardian\\\">Guardian</a>. He was previously the Guardian's Scotland correspondent. He has worked as a home affairs, environment and politics correspondent for the Scotsman and Scotland on Sunday, and as a senior reporter with the Independent and Independent on Sunday</p><p>• <a href=\\\"http://static.guim.co.uk/ni/1418406968273/Severin-Carrell-PGP-key.txt\\\"> Severin Carrell's public key</a></p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/contributor/2007/09/28/severin_carrell_140x140.jpg\",\"firstName\":\"carrell\",\"lastName\":\"severin\"},{\"id\":\"profile/matthewweaver\",\"type\":\"contributor\",\"webTitle\":\"Matthew Weaver\",\"webUrl\":\"https://www.theguardian.com/profile/matthewweaver\",\"apiUrl\":\"https://content.guardianapis.com/profile/matthewweaver\",\"references\":[],\"bio\":\"<p>Matthew Weaver is a reporter on <br /><a href=\\\"http://www.guardian.co.uk\\\">guardian.co.uk</a>. He received the digital editorial individual award at the <a href=\\\"http://www.ukaop.org.uk/news/2010aopawardwinners2131.html\\\">AOP awards 2010</a>. Before joining the Guardian website in 2001 he was news editor of the architecture magazine Building Design. You can follow him on Twitter <a href=\\\"http://twitter.com/matthew_weaver\\\">@matthew_weaver</a></p>\",\"bylineImageUrl\":\"https://uploads.guim.co.uk/2017/12/26/Matthew-Weaver.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2017/12/26/Matthew_Weaver,_L.png\",\"firstName\":\"Matthew\",\"lastName\":\"Weaver\"},{\"id\":\"profile/jim-waterson\",\"type\":\"contributor\",\"webTitle\":\"Jim Waterson\",\"webUrl\":\"https://www.theguardian.com/profile/jim-waterson\",\"apiUrl\":\"https://content.guardianapis.com/profile/jim-waterson\",\"references\":[],\"bio\":\"<p>Jim Waterson is the Guardian's media editor. He was previously BuzzFeed's political editor.</p>\",\"firstName\":\"Jim \",\"lastName\":\"Waterson\"},{\"id\":\"profile/libbybrooks\",\"type\":\"contributor\",\"webTitle\":\"Libby Brooks\",\"webUrl\":\"https://www.theguardian.com/profile/libbybrooks\",\"apiUrl\":\"https://content.guardianapis.com/profile/libbybrooks\",\"references\":[],\"bio\":\"<p>Libby Brooks is the Guardian's Scotland correspondent, based in Glasgow</p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/contributor/2014/5/5/1399294269797/Libby-Brooks.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2017/10/06/Libby-Brooks,-L.png\",\"firstName\":\"Libby\",\"lastName\":\"Brooks\"},{\"id\":\"profile/jamie-grierson\",\"type\":\"contributor\",\"webTitle\":\"Jamie Grierson\",\"webUrl\":\"https://www.theguardian.com/profile/jamie-grierson\",\"apiUrl\":\"https://content.guardianapis.com/profile/jamie-grierson\",\"references\":[],\"bio\":\"<p>Jamie Grierson is the Guardian's home affairs editor. Twitter&nbsp;<a href=\\\"https://twitter.com/jamiegrierson?lang=en-gb\\\">@JamieGrierson</a></p>\",\"bylineImageUrl\":\"https://static.guim.co.uk/sys-images/Guardian/Pix/contributor/2015/7/21/1437482178282/Jamie-Grierson.jpg\",\"bylineLargeImageUrl\":\"https://uploads.guim.co.uk/2017/10/06/Jamie-Grierson,-L.png\",\"firstName\":\"Jamie\",\"lastName\":\"Grierson\",\"twitterHandle\":\"JamieGrierson\"}],\"isHosted\":false,\"pillarId\":\"pillar/news\",\"pillarName\":\"News\"}]}}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list_view);

        ArrayList<Article> articles = QueryUtils.parseJSON(jsonTest);

        ArticleAdapter articleAdapter = new ArticleAdapter(this, articles);
        listView.setAdapter(articleAdapter);
    }
}
