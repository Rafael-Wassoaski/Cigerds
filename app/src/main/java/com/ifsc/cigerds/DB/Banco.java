package com.ifsc.cigerds.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Banco extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String NOME_BANCO = "vistorias.db";
    private static final String TABELA = "vistoria";
    private static final String ID = "_id";
    private static final String AUTOR = "autor";
    private static final String COBRAD = "cobrad";
    private static final String MUNICIPIO = "municipio";


    private static final String DESCRICAO = "descricao";
    private static final String DATA = "data";
    private static final String ENDERECO = "endereco";

    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";

    private static final String DANOS_HUMANOS_DESALOJADOS = "danos_humanos_desalojados";
    private static final String DANOS_HUMANOS_DESABRIGADOS = "danos_humanos_desabrigados";
    private static final String DANOS_HUMANOS_DESAPARECIDOS = "danos_humanos_desaparecidos";
    private static final String DANOS_HUMANOS_FERIDOS = "danos_humanos_feridos";
    private static final String DANOS_HUMANOS_ENFERMOS = "danos_humanos_enfermos";
    private static final String DANOS_HUMANOS_MORTOS = "danos_humanos_mortos";
    private static final String DANOS_HUMANOS_ISOLADOS = "danos_humanos_isolados";
    private static final String DANOS_HUMANOS_ATINGIDOS = "danos_humanos_atingidos";
    private static final String DANOS_HUMANOS_AFETADOS = "danos_humanos_afetados";
    private static final String DANOS_HUMANOS_OBSERVAÇÕES = "danos_humanos_observacoes";

    private static final String UNIDADES_HABITACIONAIS_ANTINGIDAS = "unidades_habitacionais_atingidas";
    private static final String UNIDADES_HABITACIONAIS_DANIFICADAS = "unidades_habitacionais_danificads";
    private static final String UNIDADES_HABITACIONAIS_INTERDITADAS = "unidades_habitacionais_interditadas";
    private static final String UNIDADES_HABITACIONAIS_DESTRUIDAS = "unidades_habitacionais_destruidas";
    private static final String INSTALACOES_PUBLICAS_SAUDE_ATINGIDAS = "instalacoes_publicas_saude_atingidas";
    private static final String INSTALACOES_PUBLICAS_ENSINO_ATINGIDAS = "instalacoes_publicas_ensino_atingidas";
    private static final String INSTALACOES_COMUNITARIAS_ATINGIDAS = "instalacoes_comunitarias_atingidas";
    private static final String OBRAS_ATINGIDAS = "obras_atingidas";
    private static final String INTERRUPCOES_SERVICOS_ESSENCIAIS = "interrupcoes_servicos_essenciais";
    private static final String DANOS_MATERIAIS_OBSERVACOES = "danos_materiais_observacoes";

    private static final String CONTAMINACAO_SOLO = "contaminacao_solo";
    private static final String CONTAMINACAO_AGUA = "contaminacao_agua";
    private static final String CONTAMINACAO_AR = "contaminacao_ar";
    private static final String DANOS_AMBIENTAIS_OBSERVACOES = "danos_ambientais_observacoes";

    private static final String DANOS_AGRICULTURA = "danos_agricultura";
    private static final String DANOS_PECUARIA = "danos_pecuaria";
    private static final String DANOS_INDUSTRIA = "danos_industria";
    private static final String DANOS_COMERCIO = "danos_comercio";
    private static final String DANOS_PRESTACAO_DE_SERVICOS = "danos_prestacao_de_servicos";
    private static final String DANOS_ECONOMICOS_OBSERVACOES = "danos_economicos_observacoes";


    private static final String IAH_CESTAS_DE_ALIMENTOS = "iah_cestas_de_alimentos";
    private static final String IAH_AGUA_POTAVEL = "iah_agua_potavel";
    private static final String IAH_COLCHOES = "iah_colchoes";
    private static final String IAH_KIT_HIGIENE_PESSOAL = "iah_kit_higiene_pessoal";
    private static final String IAH_KIT_LIMPEZA = "iah_kit_limpeza";
    private static final String IAH_TELHAS = "iah_telhas";
    private static final String IAH_LONA_PLASTICA = "iah_lona_plastica";
    private static final String IAH_OUTROS = "iah_outros";
    private static final String IAH_FONECIDOS_OUTROS_OBSERVACOES = "iah_fornecidos_outros_observacoes";
    private static final String IAH_VIAS_PUBLICAS_TOTALMENTE_DESOBISTRUIDAS = "iah_vias_publicas_totalmente_desobistruidas";
    private static final String IAH_REESTABELECIMENTO_SERVICOS_ESSENCIAIS = "iah_reestabelecimento_servicos_essenciais";













    public Banco(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sqlCreateTable = String.format(
          "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s INTEGER, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT," +
                  "%s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s TEXT," +
                  " %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s TEXT," +
                  "%s INTEGER, %s INTEGER, %s INTEGER, %s TEXT," +
                  "%s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s TEXT," +
                  "%s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s TEXT, %s INTEGER, %s INTEGER)",
                ID, AUTOR, COBRAD, MUNICIPIO, DESCRICAO, DATA, ENDERECO, DANOS_HUMANOS_DESALOJADOS, DANOS_HUMANOS_DESABRIGADOS, DANOS_HUMANOS_DESAPARECIDOS, DANOS_HUMANOS_FERIDOS, DANOS_HUMANOS_ENFERMOS, DANOS_HUMANOS_MORTOS, DANOS_HUMANOS_ISOLADOS, DANOS_HUMANOS_ATINGIDOS, DANOS_HUMANOS_OBSERVAÇÕES,
                UNIDADES_HABITACIONAIS_ANTINGIDAS, UNIDADES_HABITACIONAIS_DANIFICADAS, UNIDADES_HABITACIONAIS_INTERDITADAS, UNIDADES_HABITACIONAIS_DESTRUIDAS, INSTALACOES_PUBLICAS_SAUDE_ATINGIDAS, INSTALACOES_PUBLICAS_ENSINO_ATINGIDAS, INSTALACOES_COMUNITARIAS_ATINGIDAS, OBRAS_ATINGIDAS, INTERRUPCOES_SERVICOS_ESSENCIAIS, DANOS_MATERIAIS_OBSERVACOES,
                CONTAMINACAO_SOLO, CONTAMINACAO_AGUA, CONTAMINACAO_AR, DANOS_AMBIENTAIS_OBSERVACOES,
                DANOS_AGRICULTURA, DANOS_PECUARIA, DANOS_INDUSTRIA, DANOS_COMERCIO, DANOS_PRESTACAO_DE_SERVICOS, DANOS_ECONOMICOS_OBSERVACOES,
                IAH_CESTAS_DE_ALIMENTOS, IAH_AGUA_POTAVEL, IAH_COLCHOES, IAH_KIT_HIGIENE_PESSOAL, IAH_KIT_LIMPEZA, IAH_TELHAS, IAH_LONA_PLASTICA, IAH_OUTROS, IAH_FONECIDOS_OUTROS_OBSERVACOES, IAH_VIAS_PUBLICAS_TOTALMENTE_DESOBISTRUIDAS, IAH_REESTABELECIMENTO_SERVICOS_ESSENCIAIS

        );

        Log.d("SQL", sqlCreateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
