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

/**
 * ApprovalFlowRoutesIndexResponseApprovalFlowRoutes
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class ApprovalFlowRoutesIndexResponseApprovalFlowRoutes {
  public static final String SERIALIZED_NAME_DEFAULT_ROUTE = "default_route";
  @SerializedName(SERIALIZED_NAME_DEFAULT_ROUTE)
  private Boolean defaultRoute;

  public static final String SERIALIZED_NAME_DEFINITION_SYSTEM = "definition_system";
  @SerializedName(SERIALIZED_NAME_DEFINITION_SYSTEM)
  private Boolean definitionSystem;

  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private String description;

  public static final String SERIALIZED_NAME_FIRST_STEP_ID = "first_step_id";
  @SerializedName(SERIALIZED_NAME_FIRST_STEP_ID)
  private Integer firstStepId;

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Integer id;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_REQUEST_FORM_IDS = "request_form_ids";
  @SerializedName(SERIALIZED_NAME_REQUEST_FORM_IDS)
  private List<Integer> requestFormIds = null;

  /**
   * Gets or Sets usages
   */
  @JsonAdapter(UsagesEnum.Adapter.class)
  public enum UsagesEnum {
    TXNAPPROVAL("TxnApproval"),
    
    EXPENSEAPPLICATION("ExpenseApplication"),
    
    PAYMENTREQUEST("PaymentRequest"),
    
    APPROVALREQUEST("ApprovalRequest"),
    
    DOCAPPROVAL("DocApproval");

    private String value;

    UsagesEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static UsagesEnum fromValue(String value) {
      for (UsagesEnum b : UsagesEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<UsagesEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final UsagesEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public UsagesEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return UsagesEnum.fromValue(value);
      }
    }
  }

  public static final String SERIALIZED_NAME_USAGES = "usages";
  @SerializedName(SERIALIZED_NAME_USAGES)
  private List<UsagesEnum> usages = null;

  public static final String SERIALIZED_NAME_USER_ID = "user_id";
  @SerializedName(SERIALIZED_NAME_USER_ID)
  private Integer userId;


  public ApprovalFlowRoutesIndexResponseApprovalFlowRoutes defaultRoute(Boolean defaultRoute) {
    
    this.defaultRoute = defaultRoute;
    return this;
  }

   /**
   * 基本経路として設定されているかどうか&lt;br&gt;&lt;br&gt; リクエストパラメータusageに下記のいずれかが指定され、かつ、基本経路の場合はtrueになります。 * &#x60;TxnApproval&#x60; - 仕訳承認 * &#x60;ExpenseApplication&#x60; - 経費精算 * &#x60;PaymentRequest&#x60; - 支払依頼 * &#x60;ApprovalRequest&#x60;(リクエストパラメータrequest_form_idを同時に指定) - 各種申請 * &#x60;DocApproval&#x60; - 請求書等 (見積書・納品書・請求書・発注書)  &lt;a href&#x3D;\&quot;https://support.freee.co.jp/hc/ja/articles/900000507963-%E7%94%B3%E8%AB%8B%E3%83%95%E3%82%A9%E3%83%BC%E3%83%A0%E3%81%AE%E5%9F%BA%E6%9C%AC%E7%B5%8C%E8%B7%AF%E8%A8%AD%E5%AE%9A\&quot; target&#x3D;\&quot;_blank\&quot;&gt;申請フォームの基本経路設定&lt;/a&gt; 
   * @return defaultRoute
  **/
  @ApiModelProperty(example = "true", required = true, value = "基本経路として設定されているかどうか<br><br> リクエストパラメータusageに下記のいずれかが指定され、かつ、基本経路の場合はtrueになります。 * `TxnApproval` - 仕訳承認 * `ExpenseApplication` - 経費精算 * `PaymentRequest` - 支払依頼 * `ApprovalRequest`(リクエストパラメータrequest_form_idを同時に指定) - 各種申請 * `DocApproval` - 請求書等 (見積書・納品書・請求書・発注書)  <a href=\"https://support.freee.co.jp/hc/ja/articles/900000507963-%E7%94%B3%E8%AB%8B%E3%83%95%E3%82%A9%E3%83%BC%E3%83%A0%E3%81%AE%E5%9F%BA%E6%9C%AC%E7%B5%8C%E8%B7%AF%E8%A8%AD%E5%AE%9A\" target=\"_blank\">申請フォームの基本経路設定</a> ")

  public Boolean getDefaultRoute() {
    return defaultRoute;
  }


  public void setDefaultRoute(Boolean defaultRoute) {
    this.defaultRoute = defaultRoute;
  }


  public ApprovalFlowRoutesIndexResponseApprovalFlowRoutes definitionSystem(Boolean definitionSystem) {
    
    this.definitionSystem = definitionSystem;
    return this;
  }

   /**
   * システム作成の申請経路かどうか
   * @return definitionSystem
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "true", value = "システム作成の申請経路かどうか")

  public Boolean getDefinitionSystem() {
    return definitionSystem;
  }


  public void setDefinitionSystem(Boolean definitionSystem) {
    this.definitionSystem = definitionSystem;
  }


  public ApprovalFlowRoutesIndexResponseApprovalFlowRoutes description(String description) {
    
    this.description = description;
    return this;
  }

   /**
   * 申請経路の説明
   * @return description
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "申請経路の説明", value = "申請経路の説明")

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }


  public ApprovalFlowRoutesIndexResponseApprovalFlowRoutes firstStepId(Integer firstStepId) {
    
    this.firstStepId = firstStepId;
    return this;
  }

   /**
   * 最初の承認ステップのID
   * minimum: 1
   * maximum: 2147483647
   * @return firstStepId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "1", value = "最初の承認ステップのID")

  public Integer getFirstStepId() {
    return firstStepId;
  }


  public void setFirstStepId(Integer firstStepId) {
    this.firstStepId = firstStepId;
  }


  public ApprovalFlowRoutesIndexResponseApprovalFlowRoutes id(Integer id) {
    
    this.id = id;
    return this;
  }

   /**
   * 申請経路ID
   * minimum: 1
   * maximum: 2147483647
   * @return id
  **/
  @ApiModelProperty(example = "1", required = true, value = "申請経路ID")

  public Integer getId() {
    return id;
  }


  public void setId(Integer id) {
    this.id = id;
  }


  public ApprovalFlowRoutesIndexResponseApprovalFlowRoutes name(String name) {
    
    this.name = name;
    return this;
  }

   /**
   * 申請経路名
   * @return name
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "申請経路", value = "申請経路名")

  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public ApprovalFlowRoutesIndexResponseApprovalFlowRoutes requestFormIds(List<Integer> requestFormIds) {
    
    this.requestFormIds = requestFormIds;
    return this;
  }

  public ApprovalFlowRoutesIndexResponseApprovalFlowRoutes addRequestFormIdsItem(Integer requestFormIdsItem) {
    if (this.requestFormIds == null) {
      this.requestFormIds = new ArrayList<>();
    }
    this.requestFormIds.add(requestFormIdsItem);
    return this;
  }

   /**
   * 申請経路で利用できる申請フォームID配列
   * @return requestFormIds
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "申請経路で利用できる申請フォームID配列")

  public List<Integer> getRequestFormIds() {
    return requestFormIds;
  }


  public void setRequestFormIds(List<Integer> requestFormIds) {
    this.requestFormIds = requestFormIds;
  }


  public ApprovalFlowRoutesIndexResponseApprovalFlowRoutes usages(List<UsagesEnum> usages) {
    
    this.usages = usages;
    return this;
  }

  public ApprovalFlowRoutesIndexResponseApprovalFlowRoutes addUsagesItem(UsagesEnum usagesItem) {
    if (this.usages == null) {
      this.usages = new ArrayList<>();
    }
    this.usages.add(usagesItem);
    return this;
  }

   /**
   * 申請種別（申請経路を使用できる申請種別を示します。例えば、ApprovalRequest の場合は、各種申請で使用できる申請経路です。） * &#x60;TxnApproval&#x60; - 仕訳承認 * &#x60;ExpenseApplication&#x60; - 経費精算 * &#x60;PaymentRequest&#x60; - 支払依頼 * &#x60;ApprovalRequest&#x60; - 各種申請 * &#x60;DocApproval&#x60; - 請求書等 (見積書・納品書・請求書・発注書)
   * @return usages
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "申請種別（申請経路を使用できる申請種別を示します。例えば、ApprovalRequest の場合は、各種申請で使用できる申請経路です。） * `TxnApproval` - 仕訳承認 * `ExpenseApplication` - 経費精算 * `PaymentRequest` - 支払依頼 * `ApprovalRequest` - 各種申請 * `DocApproval` - 請求書等 (見積書・納品書・請求書・発注書)")

  public List<UsagesEnum> getUsages() {
    return usages;
  }


  public void setUsages(List<UsagesEnum> usages) {
    this.usages = usages;
  }


  public ApprovalFlowRoutesIndexResponseApprovalFlowRoutes userId(Integer userId) {
    
    this.userId = userId;
    return this;
  }

   /**
   * 更新したユーザーのユーザーID
   * minimum: 1
   * maximum: 2147483647
   * @return userId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "1", value = "更新したユーザーのユーザーID")

  public Integer getUserId() {
    return userId;
  }


  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ApprovalFlowRoutesIndexResponseApprovalFlowRoutes approvalFlowRoutesIndexResponseApprovalFlowRoutes = (ApprovalFlowRoutesIndexResponseApprovalFlowRoutes) o;
    return Objects.equals(this.defaultRoute, approvalFlowRoutesIndexResponseApprovalFlowRoutes.defaultRoute) &&
        Objects.equals(this.definitionSystem, approvalFlowRoutesIndexResponseApprovalFlowRoutes.definitionSystem) &&
        Objects.equals(this.description, approvalFlowRoutesIndexResponseApprovalFlowRoutes.description) &&
        Objects.equals(this.firstStepId, approvalFlowRoutesIndexResponseApprovalFlowRoutes.firstStepId) &&
        Objects.equals(this.id, approvalFlowRoutesIndexResponseApprovalFlowRoutes.id) &&
        Objects.equals(this.name, approvalFlowRoutesIndexResponseApprovalFlowRoutes.name) &&
        Objects.equals(this.requestFormIds, approvalFlowRoutesIndexResponseApprovalFlowRoutes.requestFormIds) &&
        Objects.equals(this.usages, approvalFlowRoutesIndexResponseApprovalFlowRoutes.usages) &&
        Objects.equals(this.userId, approvalFlowRoutesIndexResponseApprovalFlowRoutes.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(defaultRoute, definitionSystem, description, firstStepId, id, name, requestFormIds, usages, userId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ApprovalFlowRoutesIndexResponseApprovalFlowRoutes {\n");
    sb.append("    defaultRoute: ").append(toIndentedString(defaultRoute)).append("\n");
    sb.append("    definitionSystem: ").append(toIndentedString(definitionSystem)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    firstStepId: ").append(toIndentedString(firstStepId)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    requestFormIds: ").append(toIndentedString(requestFormIds)).append("\n");
    sb.append("    usages: ").append(toIndentedString(usages)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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

