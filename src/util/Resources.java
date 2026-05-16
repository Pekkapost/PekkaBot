package util;

/**
 * Tracked-in-git string content the bot ships with.
 *
 * Anything that's a secret or per-host (token, owner ids, prefix) stays
 * in {@link config.BotConstants}; everything else — invite URL, action
 * GIF URLs, the Shion suffix — lives here so it's shared across clones
 * instead of having to be re-typed into each developer's local
 * BotConstants.
 *
 * Field names are preserved verbatim from the original BotConstants so
 * the migration was a one-pass import swap at every call site. Fill in
 * the URL strings from your existing local BotConstants the first time
 * you check this file out.
 */
public class Resources {
    // OAuth2 invite URL posted by /AddMe. Generate via the Developer Portal
    // (OAuth2 → URL Generator → scopes: bot, applications.commands).
    public static final String addME = "Add me at https://discordapp.com/oauth2/authorize?&client_id=379513566711119872&scope=bot&permissions=1208470592";

    // Discord CDN GIF/PNG URLs displayed by the action commands. Each one
    // is rendered as the embed image by EmbedManager.action(...).
    public static final String hug = "https://cdn.discordapp.com/attachments/814291379194036245/1214149766586572870/hug.gif?ex=65f81016&is=65e59b16&hm=0871a4499d552a1afbe783e30296d198f591d0b91b67301cb864fc3876c10ab8&";
    public static final String pat = "https://cdn.discordapp.com/attachments/814291379194036245/1214159002221412362/kanna-kamui-pat.gif?ex=65f818b0&is=65e5a3b0&hm=68fd899b3bf2855dd6356adf4ac2b32244a11205249ee8de0906942282204db6&";
    public static final String scold = "https://cdn.discordapp.com/attachments/814291379194036245/1214149916872417300/oreimo-dumb.gif?ex=65f81039&is=65e59b39&hm=6157c9ee538e315ccec036e9617e29c7796397a4778181e5243b9fd80a655ff8&";
    public static final String slam = "https://cdn.discordapp.com/attachments/814291379194036245/1214158348199399444/tic-elder-sister-plastic-neesan.gif?ex=65f81814&is=65e5a314&hm=aebf7d966ae4ba109b14421ae76cb8ab60b1a4d958e0aba7355bf70fdfe7248f&";
    public static final String slap = "https://cdn.discordapp.com/attachments/814291379194036245/1214158787632562196/anime-slap-mad.gif?ex=65f8187c&is=65e5a37c&hm=1260480f9f8d156bb2cf6d6aa75dc104e357856633aff4ca1811d452b06839d8&";

    // Suffix appended to the Shion counter announcement message.
    public static final String shion = "<:Shion_point:778018004075937803>";

    // Two of the three random image branches for the Tiramisu command
    // (the third pulls from GaryManager).
    public static final String tiramisuCake = "https://cdn.discordapp.com/attachments/814291379194036245/1214159163706449961/images.jpg?ex=65f818d6&is=65e5a3d6&hm=72ab62edea5104c0572946fbb6dae02bf9d8b2f69835141ee1ab425a7297fb00&";
    public static final String tiramisuCharacter = "https://cdn.discordapp.com/attachments/814291379194036245/1214159398092541962/101040121_rank5_base.png?ex=65f8190e&is=65e5a40e&hm=2ddf72bbc05a2f27e7f68c0aefc07a05169a126b59ea66a539d26f741fa042aa&";
}
