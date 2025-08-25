# Figure Editor Swing (Maven + Java 17)

Aplicação simples em **Java Swing**: **clique** para inserir uma figura na tela.
- **Clique**: insere figura do tamanho padrão.
- **Clique + Arraste**: define o tamanho (pré-visualização tracejada).
- **[C]** Círculo • **[R]** Retângulo • **[Del]** Limpar • **Botão "Cor..."** escolhe a cor.

## Rodar
```bash
mvn -q exec:java
```
> Requer Maven e JDK 17+

## Estrutura
```
src/main/java/br/com/mariojp/figureeditor/
  App.java            # main + janela
  DrawingPanel.java   # painel de desenho (mouse/teclado)
  ToolbarFactory.java # toolbar
  StatusBar.java      # barra de status
```

## Ideias de evolução
- Seleção/movimentação de figuras
- Camadas e alinhamento magnético
- Exportar PNG/SVG
- Undo/Redo (Memento + Command)
