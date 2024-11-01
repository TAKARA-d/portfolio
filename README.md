# 書籍販売システム（仮想書店のECサイト）

## プロジェクト概要
このプロジェクトは、仮想の「町の本屋」をモデルとしたネット通販システムの構築を目的としています。電子書籍の普及や通販の拡大により経営状況が悪化している町の本屋が、書籍販売や需要把握をデジタル化することで販路を拡大し、顧客のニーズに応える仕組みを構築しました。グループワークを通じて、チームで協力しながらシステム開発を行いました。

## 使用技術
- **プログラミング言語**: Java, JSP
- **データベース**: MySQL
- **アーキテクチャ**: MVCモデル
- **その他**: CSV出力機能、AIによるコード修正補助

## 担当内容
- **ビュー（View）**: JSPファイルを用いてユーザーインターフェースを構築し、ユーザーが利用しやすいデザインと操作性を目指しました。
- **リーダー**: チーム全体の進捗管理と役割分担を行い、メンバーが協力して開発を進められる環境を整備しました。

## 主な機能
- **ログイン・会員登録**: ユーザーはアカウントを作成し、ログインしてシステムを利用できます。
- **カート機能**: 選択した書籍をカートに追加し、購入手続きが可能です。
- **管理者ログイン**: 管理者専用IDでの管理者ページへのログイン処理。
- **売上順ソート**: 管理者ページから売れ筋の書籍と年齢層を確認できます。

## システム構成
- **顧客情報データの管理**: 会員登録したユーザー情報をデータベースで管理し、ユーザーにパーソナライズされた体験を提供します。
- **ログイン機能と会員管理**: 一般ユーザーと管理者のアクセスレベルを分けて、安全なシステムを実現しています。
- **売上データの収集と分析**: 売上データを蓄積し、売上順に表示する機能で需要の把握を支援します。

## ディレクトリ構成とファイルの用途

### 1. **JSPファイル**
   - **login.jsp**: ログインページ。ユーザーがログイン情報を入力するためのページ。![アプリのスクリーンショッ](ec_takara/src/main/webapp/image/image1.png)
   - **fail.jsp**: エラーページ。ログインが失敗した際に表示されます。
   - **member.jsp**: 会員登録ページ。会員情報を入力します。![アプリのスクリーンショッ](ec_takara/src/main/webapp/image/image2.png)
   - **member_check.jsp**:入力された会員登録情報の確認ページです。![アプリのスクリーンショッ](ec_takara/src/main/webapp/image/image3.png)
   - **shohin.jsp**:商品の一覧を表示するページで、各商品をクリックすると詳細ページに遷移します。![アプリのスクリーンショッ](ec_takara/src/main/webapp/image/image4.png)
   - **syousai.jsp**: 商品詳細ページ。選択した商品の詳細情報を表示。![アプリのスクリーンショッ](ec_takara/src/main/webapp/image/image5.png)
   - **cart.jsp**: カートページ。選択した商品を確認・削除できます。![アプリのスクリーンショッ](ec_takara/src/main/webapp/image/image6.png)
   - **buy.jsp**: 購入手続き開始ページ。カートの内容を表示し、購入を進めるボタンが表示されます。![アプリのスクリーンショッ](ec_takara/src/main/webapp/image/image7.png)
   - **complete.jsp**: 購入完了ページ。購入が成功したことをユーザーに通知します。![アプリのスクリーンショッ](ec_takara/src/main/webapp/image/image8.png)
   - **total.jsp**: 管理者ページのトップページ。管理者が売上やユーザー活動の概要を効率的に確認・管理する。
   - **bought.jsp**: 購入の詳細を年齢層やジャンルごとに一覧表示。フィルタリングやソート機能が含まれています。![アプリのスクリーンショッ](ec_takara/src/main/webapp/image/image10.png)
   - **bought2.jsp**: bought.jspと連携し、さらに詳細な情報や購買データを確認。カテゴリ分けや年齢層別の集計を表示します。![アプリのスクリーンショッ](ec_takara/src/main/webapp/image/image11.png)
   - **logout.jsp**: ログアウト完了ページ。セッションを終了し、ログアウト完了を表示。![アプリのスクリーンショッ](ec_takara/src/main/webapp/image/image9.png)
### 2. **Javaコントローラーとビーン**
   - **LoginFilter.java**: ログイン状態を確認し、未ログインユーザーをリダイレクトするフィルタ。
   - **LoginController.java**: ログイン処理を管理するサーブレット。
   - **LogoutController.java**: ログアウト処理を行うサーブレット。
   - **LoginBean.java**: ログイン情報を管理するBeanクラス。ログイン認証用。
   - **MemberBean.java**: 会員情報を管理するBeanクラス。
   - **ToListController.java**: 商品一覧ページに遷移するサーブレット。
   - **ShouhinToShousaiController.java**: 商品一覧から詳細ページへ遷移するサーブレット。
   - **ShousaiToCart.java**: 商品詳細ページからカートに商品を追加するサーブレット。
   - **CartBean.java**: カート内の商品情報を管理するBeanクラス。
   - **CartDeleteController.java**: カートから商品を削除するサーブレット。
   - **GoodBean.java**: 商品情報を管理するBeanクラス。商品データを取得・保持します。
   - **ToConfirmController.java**: 購入確認ページに遷移するサーブレット。
   - **OperationPageController.java**: 管理者ページの操作を処理するサーブレット。
   - **ToBuyController.java**: 購入確認後の処理を行うサーブレット。
   - **OperationTototalController.java**: 管理者の操作確認用サーブレット。
   - **PageBean.java**: 管理者ページのページング情報を管理するBeanクラス。
   - **BoughtTototalController.java**: 購入後の確認画面へ遷移するサーブレット。
   - **BoughtBean.java**: 購入履歴を管理するBeanクラス。
   - **TakaraBean.java**: ユーザー情報と商品情報を保持するBeanクラス。
     
### 3. **CSS**
   - **Style.css**: サイト全体のスタイルシート。背景、フォント、ボタン、リンク、テーブルなどのスタイリングを管理。

## 反省点と今後の改善
- **設計の甘さ**: 設計段階での計画不足により、開発途中での変更が多くなりました。今後は、詳細な設計と計画段階での見直しを強化し、効率的な開発を目指します。
- **チーム内の調整**: MVCモデルで分担を行いましたが、各担当が進捗状況を共有するのに苦労しました。次回は定期的な進捗確認を行い、スムーズな情報共有を実現したいと考えています。
- **技術的課題**: 講義内容をベースに開発を進めましたが、新しい技術の学習が必要になる場面が多くありました。AIツールを活用してコード修正の補助を行うなどの工夫をしましたが、今後は不足部分の自主学習にも取り組んでいきたいです。

## 学びと今後への活用
このプロジェクトを通じて、チーム開発での課題と解決策を学びました。システム設計の重要性、効果的なチームワーク、計画の見直しなど、今後のプロジェクトにも活かせる多くの経験を得ました。次のプロジェクトでは今回の経験を踏まえ、さらに効率的で完成度の高いシステム開発に取り組んでいきたいと考えています。

