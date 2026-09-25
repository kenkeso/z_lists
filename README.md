# ZLists

Minecraft **1.21.x** 向けの小さなプラグインです。`/z` コマンドで自分の **X座標** を表示します。

## 動作環境

- Minecraft 1.21.x (Spigot / Paper など、Bukkit API 系サーバー)
- Java 21 以上

## コマンド

| コマンド | 説明 | 権限 |
| --- | --- | --- |
| `/z` | 実行者(プレイヤー)のX座標を表示します。 | `zlists.command.z` (既定: 全員) |

実行例:

```
X座標: 123.46
```

## 設定 (config.yml)

```yaml
# 表示する小数点以下の桁数 (0-10)
decimals: 2

messages:
  player-only: "&cこのコマンドはプレイヤーのみ実行できます。"
  x: "&aX座標: &e%x%"
```

- `decimals` … 座標の小数点以下の桁数。
- `messages.x` … 表示フォーマット。`%x%` が座標に置き換わります。
- 色コードは `&` で指定できます(例: `&a` = 緑)。

## ビルド

```bash
mvn clean package
```

生成物: `target/ZLists-1.0.0.jar`

## インストール

1. `target/ZLists-1.0.0.jar` をサーバーの `plugins/` フォルダへコピーします。
2. サーバーを再起動、または `/reload confirm` を実行します。

## 開発

- `pom.xml` は 1.21 の Paper API (`provided`) に対してコンパイルします。
  最も古い 1.21 API を対象にしているため、1.21.x の各バージョンで動作します。
- 使用 API は Bukkit API のみなので、Spigot / Paper のどちらでも動作します。
