#!/bin/bash

# 🚀 Script para executar seeds do banco SENAI
# Data: 2025-06-19

echo "🎯 SENAI Database Seeds Runner"
echo "================================"

# Verificar se o PostgreSQL está disponível
if ! command -v psql &> /dev/null; then
    echo "❌ PostgreSQL não encontrado. Instale o PostgreSQL primeiro."
    exit 1
fi

# Configurações padrão (ajuste conforme necessário)
DB_HOST=${DB_HOST:-localhost}
DB_PORT=${DB_PORT:-5432}
DB_NAME=${DB_NAME:-senai}
DB_USER=${DB_USER:-postgres}

echo "📊 Configurações:"
echo "   Host: $DB_HOST"
echo "   Porta: $DB_PORT"  
echo "   Banco: $DB_NAME"
echo "   Usuário: $DB_USER"
echo ""

# Menu de opções
echo "Escolha uma opção:"
echo "1) 🚀 Executar todos os seeds"
echo "2) 🧹 Limpar banco de dados"
echo "3) 📋 Executar seed específico"
echo "4) ❌ Cancelar"
echo ""

read -p "Digite sua opção (1-4): " option

case $option in
    1)
        echo "🚀 Executando todos os seeds..."
        cd "$(dirname "$0")"
        psql -h $DB_HOST -p $DB_PORT -d $DB_NAME -U $DB_USER -f run_all_seeds.sql
        ;;
    2)
        echo "⚠️ ATENÇÃO: Isso irá remover TODOS os dados do banco!"
        read -p "Tem certeza? (digite 'SIM' para confirmar): " confirm
        if [ "$confirm" = "SIM" ]; then
            echo "🧹 Limpando banco de dados..."
            cd "$(dirname "$0")"
            psql -h $DB_HOST -p $DB_PORT -d $DB_NAME -U $DB_USER -f clean_database.sql
        else
            echo "❌ Operação cancelada."
        fi
        ;;
    3)
        echo "📋 Seeds disponíveis:"
        ls -1 *.sql | grep -E '^[0-9]' | cat -n
        echo ""
        read -p "Digite o número do seed: " seed_num
        seed_file=$(ls -1 *.sql | grep -E '^[0-9]' | sed -n "${seed_num}p")
        if [ -n "$seed_file" ]; then
            echo "📋 Executando $seed_file..."
            cd "$(dirname "$0")"
            psql -h $DB_HOST -p $DB_PORT -d $DB_NAME -U $DB_USER -f "$seed_file"
        else
            echo "❌ Seed não encontrado."
        fi
        ;;
    4)
        echo "❌ Operação cancelada."
        exit 0
        ;;
    *)
        echo "❌ Opção inválida."
        exit 1
        ;;
esac

echo ""
echo "✅ Operação concluída!"
