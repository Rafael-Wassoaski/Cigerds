package com.ifsc.cigerds.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Banco extends SQLiteOpenHelper {

    private static final int VERSAO = 3;
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



    public Banco(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        Log.d("SQLResult", "Entrou aqui");

//        String sqlCreateTable ="CREATE TABLE " + TABELA +"(" +
//                ID + "TEXT PRIMARY KEY AUTOINCREMENT, "+ AUTOR +" TEXT, " +COBRAD+" TEXT, "+MUNICIPIO+" TEXT, "+DESCRICAO+" TEXT, "+DATA+" TEXT, "+ENDERECO+" TEXT, "+DANOS_HUMANOS_DESALOJADOS+" TEXT, "+DANOS_HUMANOS_DESABRIGADOS+" TEXT, "+ DANOS_HUMANOS_DESAPARECIDOS + " TEXT, "+DANOS_HUMANOS_FERIDOS+ " TEXT, "+DANOS_HUMANOS_ENFERMOS + " TEXT, " + DANOS_HUMANOS_MORTOS + " TEXT, "+DANOS_HUMANOS_ISOLADOS+" TEXT, "+DANOS_HUMANOS_ATINGIDOS +" TEXT, " + DANOS_HUMANOS_OBSERVAÇÕES+" TEXT, "+
//                UNIDADES_HABITACIONAIS_ANTINGIDAS+" TEXT, "+UNIDADES_HABITACIONAIS_DANIFICADAS+" TEXT, " + UNIDADES_HABITACIONAIS_INTERDITADAS+" TEXT, "+ UNIDADES_HABITACIONAIS_DESTRUIDAS+" TEXT, "+ INSTALACOES_PUBLICAS_SAUDE_ATINGIDAS +" TEXT, "+INSTALACOES_PUBLICAS_ENSINO_ATINGIDAS+" TEXT, "+INSTALACOES_COMUNITARIAS_ATINGIDAS+" TEXT, "+OBRAS_ATINGIDAS+" TEXT, "+INTERRUPCOES_SERVICOS_ESSENCIAIS+" TEXT, "+DANOS_MATERIAIS_OBSERVACOES+" TEXT, "+
//                CONTAMINACAO_SOLO+" TEXT, "+CONTAMINACAO_AGUA+" TEXT, "+CONTAMINACAO_AR+" TEXT, "+DANOS_AMBIENTAIS_OBSERVACOES+
//                DANOS_AGRICULTURA+" TEXT, "+DANOS_PECUARIA+" TEXT, "+DANOS_INDUSTRIA+" TEXT, "+DANOS_COMERCIO+" TEXT, "+DANOS_PRESTACAO_DE_SERVICOS+" TEXT, "+DANOS_ECONOMICOS_OBSERVACOES+" TEXT, "+
//                IAH_CESTAS_DE_ALIMENTOS+" TEXT, "+IAH_AGUA_POTAVEL+" TEXT, "+IAH_COLCHOES+" TEXT, "+IAH_KIT_HIGIENE_PESSOAL+" TEXT, "+IAH_KIT_LIMPEZA+" TEXT, "+IAH_TELHAS+" TEXT, "+IAH_LONA_PLASTICA+" TEXT, "+IAH_OUTROS+" TEXT, "+IAH_FONECIDOS_OUTROS_OBSERVACOES+" TEXT, "+IAH_VIAS_PUBLICAS_TOTALMENTE_DESOBISTRUIDAS+" TEXT, "+IAH_REESTABELECIMENTO_SERVICOS_ESSENCIAIS+" TEXT "+
//                ")";
//
//
//        sqLiteDatabase.execSQL(sqlCreateTable);


//        Log.d("SQL", sqlCreateTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(sqLiteDatabase);
    }
}
