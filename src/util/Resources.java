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
    public static final String addME              = "";

    // Discord CDN GIF/PNG URLs displayed by the action commands. Each one
    // is rendered as the embed image by EmbedManager.action(...).
    public static final String hug                = "";
    public static final String pat                = "";
    public static final String scold              = "";
    public static final String slam               = "";
    public static final String slap               = "";

    // Suffix appended to the Shion counter announcement message.
    public static final String shion              = "";

    // Two of the three random image branches for the Tiramisu command
    // (the third pulls from GaryManager).
    public static final String tiramisuCake       = "";
    public static final String tiramisuCharacter  = "";
}
