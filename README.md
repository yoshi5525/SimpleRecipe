# SimpleRecipe# README

## アプリ名
Simple Recipe
(教えて！味付け)

## 概要
シンプルなレシピアプリです。  
味付けに必要な調味料のみを見ることができます。  
手元にある食材の総重量と、必要であれば重量計測に使用した調理器具の重量を入力できるようにしてあり、その数字を元に調味料を算出します。  
各料理ごとに必要な材料の目安と、調理手順も掲載しております。  

## 機能一覧
* 必要調味料の算出機能
* 非同期によるレシピ検索機能
* レシピ投稿、編集、削除機能（管理者のみが行える仕様）
* レスポンシブデザイン対応(スマホ、タブレット、PC)

## 制作背景
味付けに必要な調味料のみを見ることができます。  
食材と調味料の量がわかるレシピアプリは多々ありますが、このアプリでは手元にある食材量から逆算した調味料を算出します。  
食材も必要な分を準備するのは手間もコストもかかってしまう、使用しなかった材料は余ってしまうなどの難点を改善するために作成しました。  
各料理ごとに必要な材料の目安と、調理手順も掲載しております。  
料理をある程度したことがある方、主婦の方向けに作成しました。

## デモ
#### PC表示用  
![PC画面](https://user-images.githubusercontent.com/63286009/100419341-b6e4b880-30c7-11eb-8a08-368078a3b5c9.gif)  
#### スマホ表示用  
![スマホ画面](https://user-images.githubusercontent.com/63286009/100419352-bcda9980-30c7-11eb-84f6-4e3e9fbaa9ca.gif)

## 使用技術
* HTML
* CSS
* BootStrap
* jQuery
* Ajax通信
* Java(Servlet, JSP)
* JUnit
* Mockito
* Tomcat
* MySQL
* Github など

## 課題や今後実装したい機能
現在の検索機能は料理の名前を元に行うため、料理のカテゴリーでも検索ができる機能。  
気に入った料理をお気に入り登録する機能。

## 設計図
<img src="https://user-images.githubusercontent.com/63286009/99470770-a37b7400-2988-11eb-92a7-6aec7b96f735.png" width="400px"> <img src="https://user-images.githubusercontent.com/63286009/99470771-a4140a80-2988-11eb-818a-b1446d4a4bcb.png" width="400px"> 
<img src="https://user-images.githubusercontent.com/63286009/99471888-dde61080-298a-11eb-8244-53c029f4e4d3.png">
