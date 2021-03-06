/*
 * freee API
 *  <h1 id=\"freee_api\">freee API</h1> <hr /> <h2 id=\"start_guide\">スタートガイド</h2>  <p>freee API開発がはじめての方は<a href=\"https://developer.freee.co.jp/getting-started\">freee API スタートガイド</a>を参照してください。</p>  <hr /> <h2 id=\"specification\">仕様</h2>  <pre><code>【重要】会計freee APIの新バージョンについて 2020年12月まで、2つのバージョンが利用できる状態です。古いものは2020年12月に利用不可となります。<br> 新しいAPIを利用するにはリクエストヘッダーに以下を指定します。 X-Api-Version: 2020-06-15<br> 指定がない場合は2020年12月に廃止予定のAPIを利用することとなります。<br> 【重要】APIのバージョン指定をせずに利用し続ける場合 2020年12月に新しいバージョンのAPIに自動的に切り替わります。 詳細は、<a href=\"https://developer.freee.co.jp/release-note/2948\" target=\"_blank\">リリースノート</a>をご覧ください。<br> 旧バージョンのAPIリファレンスを確認したい場合は、<a href=\"https://freee.github.io/freee-api-schema/\" target=\"_blank\">旧バージョンのAPIリファレンスページ</a>をご覧ください。 </code></pre>  <h3 id=\"api_endpoint\">APIエンドポイント</h3>  <p>https://api.freee.co.jp/ (httpsのみ)</p>  <h3 id=\"about_authorize\">認証について</h3> <p>OAuth2.0を利用します。詳細は<a href=\"https://developer.freee.co.jp/docs\" target=\"_blank\">ドキュメントの認証</a>パートを参照してください。</p>  <h3 id=\"data_format\">データフォーマット</h3>  <p>リクエスト、レスポンスともにJSON形式をサポートしていますが、詳細は、API毎の説明欄（application/jsonなど）を確認してください。</p>  <h3 id=\"compatibility\">後方互換性ありの変更</h3>  <p>freeeでは、APIを改善していくために以下のような変更は後方互換性ありとして通知なく変更を入れることがあります。アプリケーション実装者は以下を踏まえて開発を行ってください。</p>  <ul> <li>新しいAPIリソース・エンドポイントの追加</li> <li>既存のAPIに対して必須ではない新しいリクエストパラメータの追加</li> <li>既存のAPIレスポンスに対する新しいプロパティの追加</li> <li>既存のAPIレスポンスに対するプロパティの順番の入れ変え</li> <li>keyとなっているidやcodeの長さの変更（長くする）</li> </ul>  <h3 id=\"common_response_header\">共通レスポンスヘッダー</h3>  <p>すべてのAPIのレスポンスには以下のHTTPヘッダーが含まれます。</p>  <ul> <li> <p>X-Freee-Request-ID</p> <ul> <li>各リクエスト毎に発行されるID</li> </ul> </li> </ul>  <h3 id=\"common_error_response\">共通エラーレスポンス</h3>  <ul> <li> <p>ステータスコードはレスポンス内のJSONに含まれる他、HTTPヘッダにも含まれる</p> </li> <li> <p>一部のエラーレスポンスにはエラーコードが含まれます。<br>詳細は、<a href=\"https://developer.freee.co.jp/tips/faq/40x-checkpoint\">HTTPステータスコード400台エラー時のチェックポイント</a>を参照してください</p> </li> <p>type</p>  <ul> <li>status : HTTPステータスコードの説明</li>  <li>validation : エラーの詳細の説明（開発者向け）</li> </ul> </li> </ul>  <p>レスポンスの例</p>  <pre><code>  {     &quot;status_code&quot; : 400,     &quot;errors&quot; : [       {         &quot;type&quot; : &quot;status&quot;,         &quot;messages&quot; : [&quot;不正なリクエストです。&quot;]       },       {         &quot;type&quot; : &quot;validation&quot;,         &quot;messages&quot; : [&quot;Date は不正な日付フォーマットです。入力例：2013-01-01&quot;]       }     ]   }</code></pre>  </br>  <h3 id=\"api_rate_limit\">API使用制限</h3>    <p>freeeは一定期間に過度のアクセスを検知した場合、APIアクセスをコントロールする場合があります。</p>   <p>その際のhttp status codeは403となります。制限がかかってから10分程度が過ぎると再度使用することができるようになります。</p>  <h4 id=\"reports_api_endpoint\">/reportsエンドポイント</h4>  <p>freeeは/reportsエンドポイントに対して1秒間に10以上のアクセスを検知した場合、APIアクセスをコントロールする場合があります。その際のhttp status codeは429（too many requests）となります。</p>  <p>レスポンスボディのmetaプロパティに以下を含めます。</p>  <ul>   <li>設定されている上限値</li>   <li>上限に達するまでの使用可能回数</li>   <li>（上限値に達した場合）使用回数がリセットされる時刻</li> </ul>  <h3 id=\"plan_api_rate_limit\">プラン別のAPI Rate Limit</h3>   <table border=\"1\">     <tbody>       <tr>         <th style=\"padding: 10px\"><strong>会計freeeプラン名</strong></th>         <th style=\"padding: 10px\"><strong>事業所とアプリケーション毎に1日でのAPIコール数</strong></th>       </tr>       <tr>         <td style=\"padding: 10px\">エンタープライズ</td>         <td style=\"padding: 10px\">10,000</td>       </tr>       <tr>         <td style=\"padding: 10px\">プロフェッショナル</td>         <td style=\"padding: 10px\">5,000</td>       </tr>       <tr>         <td style=\"padding: 10px\">ベーシック</td>         <td style=\"padding: 10px\">3,000</td>       </tr>       <tr>         <td style=\"padding: 10px\">ミニマム</td>         <td style=\"padding: 10px\">3,000</td>       </tr>       <tr>         <td style=\"padding: 10px\">上記以外</td>         <td style=\"padding: 10px\">3,000</td>       </tr>     </tbody>   </table>  <h3 id=\"webhook\">Webhookについて</h3>  <p>詳細は<a href=\"https://developer.freee.co.jp/docs/accounting/webhook\" target=\"_blank\">会計Webhook概要</a>を参照してください。</p>  <hr /> <h2 id=\"contact\">連絡先</h2>  <p>ご不明点、ご要望等は <a href=\"https://support.freee.co.jp/hc/ja/requests/new\">freee サポートデスクへのお問い合わせフォーム</a> からご連絡ください。</p> <hr />&copy; Since 2013 freee K.K.
 *
 * The version of the OpenAPI document: v1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package jp.co.freee.accounting.models;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jp.co.freee.accounting.models.AccountItemParamsAccountItemItems;

/**
 * AccountItemParamsAccountItem
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class AccountItemParamsAccountItem {
  public static final String SERIALIZED_NAME_ACCOUNT_CATEGORY_ID = "account_category_id";
  @SerializedName(SERIALIZED_NAME_ACCOUNT_CATEGORY_ID)
  private Integer accountCategoryId;

  public static final String SERIALIZED_NAME_ACCUMULATED_DEP_ACCOUNT_ITEM_ID = "accumulated_dep_account_item_id";
  @SerializedName(SERIALIZED_NAME_ACCUMULATED_DEP_ACCOUNT_ITEM_ID)
  private Integer accumulatedDepAccountItemId;

  public static final String SERIALIZED_NAME_CORRESPONDING_EXPENSE_ID = "corresponding_expense_id";
  @SerializedName(SERIALIZED_NAME_CORRESPONDING_EXPENSE_ID)
  private Integer correspondingExpenseId;

  public static final String SERIALIZED_NAME_CORRESPONDING_INCOME_ID = "corresponding_income_id";
  @SerializedName(SERIALIZED_NAME_CORRESPONDING_INCOME_ID)
  private Integer correspondingIncomeId;

  public static final String SERIALIZED_NAME_GROUP_NAME = "group_name";
  @SerializedName(SERIALIZED_NAME_GROUP_NAME)
  private String groupName;

  public static final String SERIALIZED_NAME_ITEMS = "items";
  @SerializedName(SERIALIZED_NAME_ITEMS)
  private List<AccountItemParamsAccountItemItems> items = null;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_PARTNERS = "partners";
  @SerializedName(SERIALIZED_NAME_PARTNERS)
  private List<AccountItemParamsAccountItemItems> partners = null;

  public static final String SERIALIZED_NAME_SEARCHABLE = "searchable";
  @SerializedName(SERIALIZED_NAME_SEARCHABLE)
  private Integer searchable;

  public static final String SERIALIZED_NAME_SHORTCUT = "shortcut";
  @SerializedName(SERIALIZED_NAME_SHORTCUT)
  private String shortcut;

  public static final String SERIALIZED_NAME_SHORTCUT_NUM = "shortcut_num";
  @SerializedName(SERIALIZED_NAME_SHORTCUT_NUM)
  private String shortcutNum;

  public static final String SERIALIZED_NAME_TAX_CODE = "tax_code";
  @SerializedName(SERIALIZED_NAME_TAX_CODE)
  private Integer taxCode;


  public AccountItemParamsAccountItem accountCategoryId(Integer accountCategoryId) {
    
    this.accountCategoryId = accountCategoryId;
    return this;
  }

   /**
   * 勘定科目カテゴリーID Selectablesフォーム用選択項目情報エンドポイント(account_groups.account_category_id)で取得可能です
   * minimum: 1
   * maximum: 2147483647
   * @return accountCategoryId
  **/
  @ApiModelProperty(example = "1", required = true, value = "勘定科目カテゴリーID Selectablesフォーム用選択項目情報エンドポイント(account_groups.account_category_id)で取得可能です")

  public Integer getAccountCategoryId() {
    return accountCategoryId;
  }


  public void setAccountCategoryId(Integer accountCategoryId) {
    this.accountCategoryId = accountCategoryId;
  }


  public AccountItemParamsAccountItem accumulatedDepAccountItemId(Integer accumulatedDepAccountItemId) {
    
    this.accumulatedDepAccountItemId = accumulatedDepAccountItemId;
    return this;
  }

   /**
   * 減価償却累計額勘定科目ID（法人のみ利用可能）
   * @return accumulatedDepAccountItemId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "1", value = "減価償却累計額勘定科目ID（法人のみ利用可能）")

  public Integer getAccumulatedDepAccountItemId() {
    return accumulatedDepAccountItemId;
  }


  public void setAccumulatedDepAccountItemId(Integer accumulatedDepAccountItemId) {
    this.accumulatedDepAccountItemId = accumulatedDepAccountItemId;
  }


  public AccountItemParamsAccountItem correspondingExpenseId(Integer correspondingExpenseId) {
    
    this.correspondingExpenseId = correspondingExpenseId;
    return this;
  }

   /**
   * 支出取引相手勘定科目ID
   * @return correspondingExpenseId
  **/
  @ApiModelProperty(example = "1", required = true, value = "支出取引相手勘定科目ID")

  public Integer getCorrespondingExpenseId() {
    return correspondingExpenseId;
  }


  public void setCorrespondingExpenseId(Integer correspondingExpenseId) {
    this.correspondingExpenseId = correspondingExpenseId;
  }


  public AccountItemParamsAccountItem correspondingIncomeId(Integer correspondingIncomeId) {
    
    this.correspondingIncomeId = correspondingIncomeId;
    return this;
  }

   /**
   * 収入取引相手勘定科目ID
   * @return correspondingIncomeId
  **/
  @ApiModelProperty(example = "1", required = true, value = "収入取引相手勘定科目ID")

  public Integer getCorrespondingIncomeId() {
    return correspondingIncomeId;
  }


  public void setCorrespondingIncomeId(Integer correspondingIncomeId) {
    this.correspondingIncomeId = correspondingIncomeId;
  }


  public AccountItemParamsAccountItem groupName(String groupName) {
    
    this.groupName = groupName;
    return this;
  }

   /**
   * 決算書表示名（小カテゴリー） Selectablesフォーム用選択項目情報エンドポイント(account_groups.name)で取得可能です
   * @return groupName
  **/
  @ApiModelProperty(example = "その他預金", required = true, value = "決算書表示名（小カテゴリー） Selectablesフォーム用選択項目情報エンドポイント(account_groups.name)で取得可能です")

  public String getGroupName() {
    return groupName;
  }


  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }


  public AccountItemParamsAccountItem items(List<AccountItemParamsAccountItemItems> items) {
    
    this.items = items;
    return this;
  }

  public AccountItemParamsAccountItem addItemsItem(AccountItemParamsAccountItemItems itemsItem) {
    if (this.items == null) {
      this.items = new ArrayList<>();
    }
    this.items.add(itemsItem);
    return this;
  }

   /**
   * 品目
   * @return items
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "品目")

  public List<AccountItemParamsAccountItemItems> getItems() {
    return items;
  }


  public void setItems(List<AccountItemParamsAccountItemItems> items) {
    this.items = items;
  }


  public AccountItemParamsAccountItem name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * 勘定科目名 (30文字以内)
   * @return name
  **/
  @ApiModelProperty(example = "新しい勘定科目", required = true, value = "勘定科目名 (30文字以内)")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public AccountItemParamsAccountItem partners(List<AccountItemParamsAccountItemItems> partners) {
    
    this.partners = partners;
    return this;
  }

  public AccountItemParamsAccountItem addPartnersItem(AccountItemParamsAccountItemItems partnersItem) {
    if (this.partners == null) {
      this.partners = new ArrayList<>();
    }
    this.partners.add(partnersItem);
    return this;
  }

   /**
   * 取引先
   * @return partners
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "取引先")

  public List<AccountItemParamsAccountItemItems> getPartners() {
    return partners;
  }


  public void setPartners(List<AccountItemParamsAccountItemItems> partners) {
    this.partners = partners;
  }


  public AccountItemParamsAccountItem searchable(Integer searchable) {
    
    this.searchable = searchable;
    return this;
  }

   /**
   * 検索可能:2, 検索不可：3(登録時未指定の場合は2で登録されます。更新時未指定の場合はsearchableは変更されません。)
   * minimum: 2
   * maximum: 3
   * @return searchable
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "2", value = "検索可能:2, 検索不可：3(登録時未指定の場合は2で登録されます。更新時未指定の場合はsearchableは変更されません。)")

  public Integer getSearchable() {
    return searchable;
  }


  public void setSearchable(Integer searchable) {
    this.searchable = searchable;
  }


  public AccountItemParamsAccountItem shortcut(String shortcut) {
    
    this.shortcut = shortcut;
    return this;
  }

   /**
   * ショートカット1 (20文字以内)
   * @return shortcut
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "NEWACCOUNTITEM", value = "ショートカット1 (20文字以内)")

  public String getShortcut() {
    return shortcut;
  }


  public void setShortcut(String shortcut) {
    this.shortcut = shortcut;
  }


  public AccountItemParamsAccountItem shortcutNum(String shortcutNum) {
    
    this.shortcutNum = shortcutNum;
    return this;
  }

   /**
   * ショートカット2(勘定科目コード)(20文字以内)
   * @return shortcutNum
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "999", value = "ショートカット2(勘定科目コード)(20文字以内)")

  public String getShortcutNum() {
    return shortcutNum;
  }


  public void setShortcutNum(String shortcutNum) {
    this.shortcutNum = shortcutNum;
  }


  public AccountItemParamsAccountItem taxCode(Integer taxCode) {
    
    this.taxCode = taxCode;
    return this;
  }

   /**
   * 税区分コード
   * minimum: 0
   * maximum: 2147483647
   * @return taxCode
  **/
  @ApiModelProperty(example = "1", required = true, value = "税区分コード")

  public Integer getTaxCode() {
    return taxCode;
  }


  public void setTaxCode(Integer taxCode) {
    this.taxCode = taxCode;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccountItemParamsAccountItem accountItemParamsAccountItem = (AccountItemParamsAccountItem) o;
    return Objects.equals(this.accountCategoryId, accountItemParamsAccountItem.accountCategoryId) &&
        Objects.equals(this.accumulatedDepAccountItemId, accountItemParamsAccountItem.accumulatedDepAccountItemId) &&
        Objects.equals(this.correspondingExpenseId, accountItemParamsAccountItem.correspondingExpenseId) &&
        Objects.equals(this.correspondingIncomeId, accountItemParamsAccountItem.correspondingIncomeId) &&
        Objects.equals(this.groupName, accountItemParamsAccountItem.groupName) &&
        Objects.equals(this.items, accountItemParamsAccountItem.items) &&
        Objects.equals(this.name, accountItemParamsAccountItem.name) &&
        Objects.equals(this.partners, accountItemParamsAccountItem.partners) &&
        Objects.equals(this.searchable, accountItemParamsAccountItem.searchable) &&
        Objects.equals(this.shortcut, accountItemParamsAccountItem.shortcut) &&
        Objects.equals(this.shortcutNum, accountItemParamsAccountItem.shortcutNum) &&
        Objects.equals(this.taxCode, accountItemParamsAccountItem.taxCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountCategoryId, accumulatedDepAccountItemId, correspondingExpenseId, correspondingIncomeId, groupName, items, name, partners, searchable, shortcut, shortcutNum, taxCode);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccountItemParamsAccountItem {\n");
    sb.append("    accountCategoryId: ").append(toIndentedString(accountCategoryId)).append("\n");
    sb.append("    accumulatedDepAccountItemId: ").append(toIndentedString(accumulatedDepAccountItemId)).append("\n");
    sb.append("    correspondingExpenseId: ").append(toIndentedString(correspondingExpenseId)).append("\n");
    sb.append("    correspondingIncomeId: ").append(toIndentedString(correspondingIncomeId)).append("\n");
    sb.append("    groupName: ").append(toIndentedString(groupName)).append("\n");
    sb.append("    items: ").append(toIndentedString(items)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    partners: ").append(toIndentedString(partners)).append("\n");
    sb.append("    searchable: ").append(toIndentedString(searchable)).append("\n");
    sb.append("    shortcut: ").append(toIndentedString(shortcut)).append("\n");
    sb.append("    shortcutNum: ").append(toIndentedString(shortcutNum)).append("\n");
    sb.append("    taxCode: ").append(toIndentedString(taxCode)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

