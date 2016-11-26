using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore.Migrations;

namespace rsc2016Quiz.Migrations
{
    public partial class AnswerCorretValue : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_QuestionEvents_Events_EventId",
                table: "QuestionEvents");

            migrationBuilder.DropIndex(
                name: "IX_QuestionEvents_EventId",
                table: "QuestionEvents");

            migrationBuilder.AddColumn<bool>(
                name: "Correct",
                table: "Answers",
                nullable: false,
                defaultValue: false);

            migrationBuilder.AddForeignKey(
                name: "FK_QuestionEvents_Events_QuestionId",
                table: "QuestionEvents",
                column: "QuestionId",
                principalTable: "Events",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_QuestionEvents_Events_QuestionId",
                table: "QuestionEvents");

            migrationBuilder.DropColumn(
                name: "Correct",
                table: "Answers");

            migrationBuilder.CreateIndex(
                name: "IX_QuestionEvents_EventId",
                table: "QuestionEvents",
                column: "EventId");

            migrationBuilder.AddForeignKey(
                name: "FK_QuestionEvents_Events_EventId",
                table: "QuestionEvents",
                column: "EventId",
                principalTable: "Events",
                principalColumn: "Id",
                onDelete: ReferentialAction.Cascade);
        }
    }
}
