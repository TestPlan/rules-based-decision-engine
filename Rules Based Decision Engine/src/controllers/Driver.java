package controllers;

import models.Rule;

/**
 * Created by Ian Markind on 10/8/2016.
 */
public class Driver {
    public static void main(String[] args) {
        Rule rule = new Rule("Rule Title", "Rule Action");
        System.out.println(rule);
    }
}
