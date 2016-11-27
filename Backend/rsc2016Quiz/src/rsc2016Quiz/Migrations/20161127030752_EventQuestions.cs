using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore.Migrations;

namespace rsc2016Quiz.Migrations
{
    public partial class EventQuestions : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "EventQuestions",
                columns: table => new
                {
                    EventId = table.Column<int>(nullable: false),
                    QuestionId = table.Column<int>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_EventQuestions", x => new { x.EventId, x.QuestionId });
                    table.ForeignKey(
                        name: "FK_EventQuestions_Events_EventId",
                        column: x => x.EventId,
                        principalTable: "Events",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                    table.ForeignKey(
                        name: "FK_EventQuestions_Questions_QuestionId",
                        column: x => x.QuestionId,
                        principalTable: "Questions",
                        principalColumn: "Id",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_EventQuestions_EventId",
                table: "EventQuestions",
                column: "EventId");

            migrationBuilder.CreateIndex(
                name: "IX_EventQuestions_QuestionId",
                table: "EventQuestions",
                column: "QuestionId");
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "EventQuestions");
        }
    }
}
